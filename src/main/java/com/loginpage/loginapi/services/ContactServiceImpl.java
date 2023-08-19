package com.loginpage.loginapi.services;

import com.loginpage.loginapi.domain.Contacts;
import com.loginpage.loginapi.exceptions.BadRequestException;
import com.loginpage.loginapi.exceptions.ResourceNotFoundException;
import com.loginpage.loginapi.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactRepository contactRepository;

    @Override
    public List<Contacts> fetchAllContacts(int userId) {
        return contactRepository.findAll(userId);
    }

    @Override
    public Contacts fetchContactById(int userId, int contactId) throws ResourceNotFoundException {
        return contactRepository.findById(userId, contactId);
    }

    @Override
    public Contacts addContact(int userId, Contacts contact) throws BadRequestException {
        int contactId = contactRepository.create(userId, contact);
        return contactRepository.findById(userId, contactId);
    }

    @Override
    public void updateContact(int userId, int contactId, Contacts contact) throws BadRequestException {
        contactRepository.update(userId, contactId, contact);
    }

    @Override
    public void removeContact(int userId, int contactId) throws ResourceNotFoundException {
        contactRepository.removeById(userId, contactId);
    }
}
