package com.esprit.message_service.controllers;

import com.esprit.message_service.entities.Conversation;
import com.esprit.message_service.entities.Message;
import com.esprit.message_service.enums.StatutMessage;
import com.esprit.message_service.repositories.ConversationRepository;
import com.esprit.message_service.repositories.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/messages") @RequiredArgsConstructor
public class MessageController {
    private final MessageRepository messages;
    private final ConversationRepository conversations;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Message send(@RequestBody Message m) {
        Conversation c = conversations.findById(m.getConversationId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Conversation introuvable"));
        Message saved = messages.save(m);
        c.setDernierMessageAt(saved.getDateEnvoi());
        conversations.save(c);
        return saved;
    }

    @GetMapping("/conversation/{cid}")
    public List<Message> history(@PathVariable String cid) {
        return messages.findByConversationIdAndSupprimeFalseOrderByDateEnvoiAsc(cid);
    }

    @GetMapping("/{id}") public Message get(@PathVariable String id) { return find(id); }

    @PutMapping("/{id}")
    public Message edit(@PathVariable String id, @RequestBody Message body) {
        Message m = find(id);
        m.setContenu(body.getContenu());
        m.setModifie(true);
        m.setDateModification(LocalDateTime.now());
        return messages.save(m);
    }

    @PatchMapping("/{id}/read")
    public Message markRead(@PathVariable String id) {
        Message m = find(id);
        m.setStatut(StatutMessage.LU);
        return messages.save(m);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {   // soft delete, like "supprime" in your design
        Message m = find(id);
        m.setSupprime(true);
        messages.save(m);
    }

    private Message find(String id) {
        return messages.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Message introuvable"));
    }
}