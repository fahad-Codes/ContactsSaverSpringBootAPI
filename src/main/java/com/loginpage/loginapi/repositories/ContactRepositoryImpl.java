package com.loginpage.loginapi.repositories;

import com.loginpage.loginapi.domain.Contacts;
import com.loginpage.loginapi.exceptions.BadRequestException;
import com.loginpage.loginapi.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class ContactRepositoryImpl implements ContactRepository {

    private static final String SQL_FIND_ALL = "SELECT CONTACT_ID, USER_ID, NAME, ADDRESS, CITY, STATE, POSTCODE, PHONE, CELL, EMAIL FROM CONTACTS WHERE USER_ID = ?";
    private static final String SQL_FIND_BY_ID = "SELECT CONTACT_ID, USER_ID, NAME, ADDRESS, CITY, STATE, POSTCODE, PHONE, CELL, EMAIL FROM CONTACTS WHERE USER_ID = ? AND CONTACT_ID = ?";
    private static final String SQL_CREATE = "INSERT INTO CONTACTS (CONTACT_ID, USER_ID, NAME, ADDRESS, CITY, STATE, POSTCODE, PHONE, CELL, EMAIL) VALUES(NEXTVAL('CONTACT_SEQ'), ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE CONTACTS SET NAME = ?, ADDRESS = ?, CITY = ?, STATE = ?, POSTCODE = ?, PHONE = ?, CELL = ?, EMAIL = ? WHERE USER_ID = ? AND CONTACT_ID = ?";
    private static final String SQL_DELETE = "DELETE FROM CONTACTS WHERE USER_ID = ? AND CONTACT_ID = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Contacts> findAll(int userId) {
        return jdbcTemplate.query(SQL_FIND_ALL, new Object[] { userId }, contactRowMapper);
    }

    @Override
    public Contacts findById(int userId, int contactId) throws ResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[] { userId, contactId }, contactRowMapper);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Contact not found");
        }
    }

    @Override
    public int create(int userId, Contacts contact) throws BadRequestException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, userId);
                ps.setString(2, contact.getName());
                ps.setString(3, contact.getAddress());
                ps.setString(4, contact.getCity());
                ps.setString(5, contact.getState());
                ps.setString(6, contact.getPostcode());
                ps.setString(7, contact.getPhone());
                ps.setString(8, contact.getCell());
                ps.setString(9, contact.getEmail());
                return ps;
            }, keyHolder);
            return (int) keyHolder.getKeys().get("CONTACT_ID");
        } catch (Exception e) {
            throw new BadRequestException("Invalid request");
        }
    }

    @Override
    public void update(int userId, int contactId, Contacts contact) throws BadRequestException {
        try {
            jdbcTemplate.update(SQL_UPDATE,
                    new Object[] { contact.getName(), contact.getAddress(), contact.getCity(), contact.getState(),
                            contact.getPostcode(), contact.getPhone(), contact.getCell(), contact.getEmail(), userId,
                            contactId });
        } catch (Exception e) {
            throw new BadRequestException("Invalid request");
        }
    }

    @Override
    public void removeById(int userId, int contactId) throws ResourceNotFoundException {
        int count = jdbcTemplate.update(SQL_DELETE, new Object[] { userId, contactId });
        if (count == 0)
            throw new ResourceNotFoundException("Contact not found");
    }

    private RowMapper<Contacts> contactRowMapper = ((rs, rowNum) -> {
        return new Contacts(rs.getInt("CONTACT_ID"),
                rs.getInt("USER_ID"),
                rs.getString("NAME"),
                rs.getString("ADDRESS"),
                rs.getString("CITY"),
                rs.getString("STATE"),
                rs.getString("POSTCODE"),
                rs.getString("PHONE"),
                rs.getString("CELL"),
                rs.getString("EMAIL"));
    });
}
