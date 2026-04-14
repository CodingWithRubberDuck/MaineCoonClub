package com.RaceKatteKlubben.MaineCoonClub.repository;

import com.RaceKatteKlubben.MaineCoonClub.config.DatabaseConfig;
import com.RaceKatteKlubben.MaineCoonClub.domain.IMemberRepository;
import com.RaceKatteKlubben.MaineCoonClub.domain.Member;
import com.RaceKatteKlubben.MaineCoonClub.exception.DataAccessException;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MySQLMemberRepository implements IMemberRepository {

    private final DatabaseConfig databaseConfig;

    public MySQLMemberRepository(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    @Override
    public List<Member> findAllMembers() {
        String sql = """
                SELECT member_id, name, email
                FROM member
                ORDER BY name
                """;
        List<Member> members = new ArrayList<>();

        try (Connection con = databaseConfig.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Member member = new Member();
                member.setMemberId(rs.getInt("member_id"));
                member.setName(rs.getString("name"));
                member.setEmail(rs.getString("email"));
                members.add(member);
            }
        } catch (SQLException sqle) {
            throw new DataAccessException("Der gik noget galt i forbindelse med databasen", sqle);
        }

        return members;
    }

    @Override
    public Optional<Member> findMemberByName(String name) {
        String sql = "SELECT * FROM member WHERE name LIKE ?";

        try (Connection con = databaseConfig.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, name + "%");
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Member member = new Member();
                member.setMemberId(rs.getInt("member_id"));
                member.setName(rs.getString("name"));
                member.setEmail(rs.getString("email"));
                return Optional.of(member);
            }

        } catch (SQLException sqle) {
            throw new DataAccessException("Der gik noget galt i forbindelse med databasen", sqle);
        }

        return Optional.empty();
    }
}
