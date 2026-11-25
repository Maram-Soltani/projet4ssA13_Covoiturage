package com.esprit.message_service.entities;

import com.esprit.message_service.enums.StatutConversation;
import com.esprit.message_service.enums.TypeConversation;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document("conversations") @Data
public class Conversation {
    @Id private String id;
    private TypeConversation type;
    private String trajetId;                 // id from the Trajet service
    private String titre;
    private StatutConversation statut = StatutConversation.ACTIVE;
    private LocalDateTime dernierMessageAt;
    private LocalDateTime dateCreation = LocalDateTime.now();
    private LocalDateTime dateArchivage;
}