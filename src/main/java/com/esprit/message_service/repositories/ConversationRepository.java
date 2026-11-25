package com.esprit.message_service.repositories;



import com.esprit.message_service.entities.Conversation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ConversationRepository extends MongoRepository<Conversation, String> {
    List<Conversation> findByTrajetId(String trajetId);
}