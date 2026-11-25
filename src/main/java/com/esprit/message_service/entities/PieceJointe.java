package com.esprit.message_service.entities;
import lombok.Data;
@Data                                      // embedded in Message, not its own collection
public class PieceJointe {
    private String url;
    private String typeMime;
    private long taille;
    private String miniatureUrl;
}