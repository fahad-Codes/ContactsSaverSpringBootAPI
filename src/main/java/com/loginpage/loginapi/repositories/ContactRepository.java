package com.loginpage.loginapi.repositories;

import com.loginpage.loginapi.domain.Contacts;
import com.loginpage.loginapi.exceptions.BadRequestException;
import com.loginpage.loginapi.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ContactRepository {

    List<Contacts> findAll(int userId);

    Contacts findById(int userId, int contactId) throws ResourceNotFoundException;

    int create(int userId, Contacts contact) throws BadRequestException;

    void update(int userId, int contactId, Contacts contact) throws BadRequestException;

    void removeById(int userId, int contactId) throws ResourceNotFoundException;

}
