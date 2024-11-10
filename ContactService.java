package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ContactService {

    @Autowired
    private ContactMessageRepository contactMessageRepository;

    public ContactMessage saveContactMessage(ContactMessage contactMessage) {
        return contactMessageRepository.save(contactMessage);
    }
    

    public List<ContactMessage> getAllContactMessages() {
        return contactMessageRepository.findAll();
    }
    public void deleteContactMessageById(Long id) {
        Optional<ContactMessage> contactMessage = contactMessageRepository.findById(id);
        if (contactMessage.isPresent()) {
            contactMessageRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Contact message not found");
        }
    }
}
