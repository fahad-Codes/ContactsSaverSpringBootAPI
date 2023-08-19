package com.loginpage.loginapi.resources;

import com.loginpage.loginapi.domain.Contacts;
import com.loginpage.loginapi.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/contacts")
public class contactsResources {

    @Autowired
    ContactService contactService;

    @GetMapping("")
    public ResponseEntity<List<Contacts>> getAllContacts(@RequestHeader("userId") int userId) {
        List<Contacts> contacts = contactService.fetchAllContacts(userId);
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @GetMapping("/{contactId}")
    public ResponseEntity<Contacts> getContactById(@RequestHeader("userId") int userId,
            @PathVariable("contactId") int contactId) {
        Contacts contact = contactService.fetchContactById(userId, contactId);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Contacts> addContact(@RequestHeader("userId") int userId,
            @RequestBody Contacts contact) {
        Contacts addedContact = contactService.addContact(userId, contact);
        return new ResponseEntity<>(addedContact, HttpStatus.CREATED);
    }

    @PutMapping("/{contactId}")
    public ResponseEntity<Map<String, Boolean>> updateContact(@RequestHeader("userId") int userId,
            @PathVariable("contactId") int contactId,
            @RequestBody Contacts contact) {
        contactService.updateContact(userId, contactId, contact);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @DeleteMapping("/{contactId}")
    public ResponseEntity<Map<String, Boolean>> deleteContact(@RequestHeader("userId") int userId,
            @PathVariable("contactId") int contactId) {
        contactService.removeContact(userId, contactId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
