package com.loginpage.loginapi.services;

import com.loginpage.loginapi.domain.Contacts;
import com.loginpage.loginapi.exceptions.BadRequestException;
import com.loginpage.loginapi.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ContactService {

    List<Contacts> fetchAllContacts(int userId);

    Contacts fetchContactById(int userId, int contactId) throws ResourceNotFoundException;

    Contacts addContact(int userId, Contacts contact) throws BadRequestException;

    void updateContact(int userId, int contactId, Contacts contact) throws BadRequestException;

    void removeContact(int userId, int contactId) throws ResourceNotFoundException;

}
