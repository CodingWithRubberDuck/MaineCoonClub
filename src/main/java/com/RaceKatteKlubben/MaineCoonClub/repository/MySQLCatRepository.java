package com.RaceKatteKlubben.MaineCoonClub.repository;

import com.RaceKatteKlubben.MaineCoonClub.config.DatabaseConfig;
import com.RaceKatteKlubben.MaineCoonClub.domain.*;
import com.RaceKatteKlubben.MaineCoonClub.exception.DataAccessException;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MySQLCatRepository implements ICatRepository {
    final DatabaseConfig databaseConfig;

    public MySQLCatRepository(DatabaseConfig databaseConfig){
        this.databaseConfig = databaseConfig;
    }

    @Override
    public void addCatToMember(Cat cat){
        String sql = "INSERT INTO cat (member_id, name, date_of_birth, date_of_death, sex, ems_code, fertile, breeder, father_cat, mother_cat) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = databaseConfig.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql))   {

            stmt.setInt(1, cat.getMemberId());
            stmt.setString(2, cat.getName());
            stmt.setDate(3, Date.valueOf(cat.getDateOfBirth()));
            // Date.valueOf kaster en nullPointerException hvis DateOfDeath er null
            if (cat.getDateOfDeath() == null){
                stmt.setDate(4, null);
            } else {
                stmt.setDate(4, Date.valueOf(cat.getDateOfDeath()));
            }
            stmt.setString(5, cat.getSex().name());
            stmt.setString(6, cat.getEmsCode().name());
            stmt.setBoolean(7, cat.isFertile());
            stmt.setString(8, cat.getBreeder());
            stmt.setString(9, cat.getFatherCat());
            stmt.setString(10, cat.getMotherCat());
            stmt.executeUpdate();
        } catch (SQLException sqle) {
            throw new DataAccessException("Der gik noget galt i forbindelse med databasen", sqle);
        }
    }

    @Override
    public List<Cat> findAllCats() {
        String sql = """
                SELECT cat_id, name, sex, ems_code, fertile, date_of_birth
                FROM cat
                ORDER BY name
                """;
        List<Cat> cats = new ArrayList<>();

        try (Connection con = databaseConfig.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cat cat = new Cat();
                cat.setCatId(rs.getInt("cat_id"));
                cat.setName(rs.getString("name"));
                cat.setSex(Sex.valueOf(rs.getString("sex")));
                cat.setEmsCode(EmsCode.valueOf(rs.getString("ems_code")));
                cat.setFertile(rs.getBoolean("fertile"));
                cat.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
                cats.add(cat);
            }
        } catch (SQLException sqle) {
            throw new DataAccessException("Der gik noget galt i forbindelse med databasen", sqle);
        }

        return cats;
    }

    @Override
    public Optional<List<Cat>> findCatByName(String name) {
        String sql = "SELECT * FROM cat WHERE name LIKE ? ORDER BY name ASC";

        try (Connection con = databaseConfig.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();

            List<Cat> cats = new ArrayList<>();

            while (rs.next()) {
                Cat cat = new Cat();
                cat.setCatId(rs.getInt("cat_id"));
                cat.setName(rs.getString("name"));
                cat.setSex(Sex.valueOf(rs.getString("sex")));
                cat.setEmsCode(EmsCode.valueOf(rs.getString("ems_code")));
                cat.setFertile(rs.getBoolean("fertile"));
                cat.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
                cats.add(cat);
            }

            return Optional.of(cats);

        } catch (SQLException sqle) {
            throw new DataAccessException("Der gik noget galt i forbindelse med databasen", sqle);
        }
    }
}
