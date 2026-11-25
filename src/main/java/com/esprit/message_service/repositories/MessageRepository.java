package com.esprit.message_service.repositories;

import com.esprit.message_service.entities.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {
    List<Message> findByConversationIdAndSupprimeFalseOrderByDateEnvoiAsc(String conversationId);
}