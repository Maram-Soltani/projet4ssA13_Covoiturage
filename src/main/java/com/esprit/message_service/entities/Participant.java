package com.esprit.message_service.entities;

import com.esprit.message_service.enums.RoleParticipant;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document("participants") @Data
public class Participant {
    @Id private String id;
    private String conversationId;           // association -> Conversation
    private String utilisateurId;            // id from the Utilisateur service
    private RoleParticipant role;
    private String dernierMessageLuId;
    private boolean enSourdine;
    private LocalDateTime dateAjout = LocalDateTime.now();
    private LocalDateTime dateSortie;
}