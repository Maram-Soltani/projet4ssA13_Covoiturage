package com.esprit.message_service.controllers;
import com.esprit.message_service.entities.Conversation;

import com.esprit.message_service.entities.Participant;
import com.esprit.message_service.repositories.ConversationRepository;
import com.esprit.message_service.repositories.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/conversations") @RequiredArgsConstructor
public class ConversationController {
    private final ConversationRepository conversations;
    private final ParticipantRepository participants;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Conversation create(@RequestBody Conversation c) { return conversations.save(c); }

    @GetMapping public List<Conversation> all() { return conversations.findAll(); }

    @GetMapping("/{id}")
    public Conversation get(@PathVariable String id) {
        return conversations.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Conversation introuvable"));
    }

    @PostMapping("/{id}/participants") @ResponseStatus(HttpStatus.CREATED)
    public Participant addParticipant(@PathVariable String id, @RequestBody Participant p) {
        p.setConversationId(id);
        return participants.save(p);
    }

    @GetMapping("/{id}/participants")
    public List<Participant> participants(@PathVariable String id) {
        return participants.findByConversationId(id);
    }
}