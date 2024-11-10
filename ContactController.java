package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "http://localhost:3000")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/submit")
    public ResponseEntity<ContactMessage> submitContactForm(@RequestBody ContactMessage contactMessage) {
        try {
            ContactMessage savedMessage = contactService.saveContactMessage(contactMessage);
            return new ResponseEntity<>(savedMessage, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<ContactMessage> getContactMessageById(@PathVariable Long id) {
//        ContactMessage contactMessage = contactService.getContactMessageById(id);
//        if (contactMessage != null) {
//            return new ResponseEntity<>(contactMessage, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//    }

    @GetMapping
    public ResponseEntity<List<ContactMessage>> getAllContactMessages() {
        try {
            List<ContactMessage> contactMessages = contactService.getAllContactMessages();
            return new ResponseEntity<>(contactMessages, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContactMessage(@PathVariable Long id) {
        try {
            contactService.deleteContactMessageById(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
