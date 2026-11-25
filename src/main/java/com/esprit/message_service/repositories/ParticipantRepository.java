package com.esprit.message_service.repositories;

import com.esprit.message_service.entities.Participant;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ParticipantRepository extends MongoRepository<Participant, String> {
    List<Participant> findByConversationId(String conversationId);
    List<Participant> findByUtilisateurId(String utilisateurId);
}