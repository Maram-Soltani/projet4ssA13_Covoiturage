package com.esprit.message_service.entities;

import com.esprit.message_service.enums.StatutMessage;
import com.esprit.message_service.enums.TypeMessage;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document("messages") @Data
public class Message {
    @Id
    private String id;
    private String conversationId;           // association -> Conversation
    private String expediteurId;
    private TypeMessage type = TypeMessage.TEXTE;
    private String contenu;
    private StatutMessage statut = StatutMessage.ENVOYE;
    private boolean modifie;
    private boolean supprime;
    private String repondAId;                // association -> another Message
    private List<PieceJointe> piecesJointes = new ArrayList<>();
    private LocalDateTime dateEnvoi = LocalDateTime.now();
    private LocalDateTime dateModification;
}