package com.loginpage.loginapi.repositories;

import com.loginpage.loginapi.domain.User;
import com.loginpage.loginapi.exceptions.EtAuthException;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final String SQL_CREATE = "INSERT INTO et_users (user_id, name, Address, City, State, post_code, phone, cell, email, password) "
            + //
            "VALUES (NEXTVAL('et_users_seq'), ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String SQL_COUNT_BY_EMAIL = "SELECT COUNT(*) FROM et_users WHERE email = ?";
    private static final String SQL_FIND_BY_ID = "SELECT user_id, name, address, city, state, post_code, phone, cell, email, password "
            +
            "FROM et_users WHERE user_id = ?";
    private static final String SQL_FIND_BY_EMAIL = "SELECT user_id, name, address, city, state, post_code, phone, cell, email, password "
            +
            "FROM et_users WHERE email = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int create(String name, String address, String city, String state, String postCode,
            String phone, String cell, String email, String password) throws EtAuthException {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, name);
                ps.setString(2, address);
                ps.setString(3, city);
                ps.setString(4, state);
                ps.setString(5, postCode);
                ps.setString(6, phone);
                ps.setString(7, cell);
                ps.setString(8, email);
                ps.setString(9, hashedPassword);
                return ps;
            }, keyHolder);
            return (int) keyHolder.getKeys().get("user_id");
        } catch (Exception e) {
            throw new EtAuthException("Invalid details. Failed to create account");
        }
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws EtAuthException {
        try {
            User user = jdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL, userRowMapper, email);
            if (!BCrypt.checkpw(password, user.getPassword()))
                throw new EtAuthException("Invalid email/password");
            return user;
        } catch (EmptyResultDataAccessException e) {
            throw new EtAuthException("Invalid email/password");
        }
    }

    @Override
    public int getCountByEmail(String email) {
        return jdbcTemplate.queryForObject(SQL_COUNT_BY_EMAIL, int.class, email);
    }

    @Override
    public User findById(int userId) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, userRowMapper, userId);
    }

    private RowMapper<User> userRowMapper = ((rs, rowNum) -> {
        return new User(rs.getInt("USER_ID"),
                rs.getString("NAME"),
                rs.getString("ADDRESS"),
                rs.getString("CITY"),
                rs.getString("STATE"),
                rs.getString("POST_CODE"),
                rs.getString("PHONE"),
                rs.getString("CELL"),
                rs.getString("EMAIL"),
                rs.getString("PASSWORD"));
    });
}
