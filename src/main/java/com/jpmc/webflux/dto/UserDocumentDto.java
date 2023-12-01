package com.jpmc.webflux.dto;

import lombok.*;

@Data
@Value
public class UserDocumentDto {

    private int userId;
    private int documentId;
    private String userName;
    private String documentName;
}
