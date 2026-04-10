package com.RaceKatteKlubben.MaineCoonClub.repository;

import com.RaceKatteKlubben.MaineCoonClub.domain.IMemberAuthRepository;
import com.RaceKatteKlubben.MaineCoonClub.config.DatabaseConfig;
import com.RaceKatteKlubben.MaineCoonClub.domain.Member;
import com.RaceKatteKlubben.MaineCoonClub.exception.DataAccessException;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class MySQLMemberAuthRepository implements IMemberAuthRepository {

    private final DatabaseConfig databaseConfig;

    public MySQLMemberAuthRepository(DatabaseConfig databaseConfig){
        this.databaseConfig = databaseConfig;
    }

    @Override
    public Member emailAlreadyExists(String email){
        String sql = "SELECT * FROM member WHERE email = ?";

        try (Connection con = databaseConfig.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql))  {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Member member = new Member();
                member.setMemberId(rs.getInt("member_id"));
                member.setName(rs.getString("name"));
                member.setEmail(rs.getString("email"));
                member.setPassword(rs.getString("password_hash"));
                return member;
            }

        } catch (SQLException sqle){
            throw new DataAccessException("Der gik noget galt i forbindelse med databasen");
        }
        return null;
    }

    public void saveNewMember(Member member){
        String sql = "INSERT INTO member (name, email, password_hash) VALUES (?, ?, ?)";

        try (Connection con = databaseConfig.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql))   {

            stmt.setString(1, member.getName());
            stmt.setString(2, member.getEmail());
            stmt.setString(3, member.getPassword());
            stmt.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new DataAccessException("Der gik noget galt i forbindelse med databasen");
        }
    }


}
