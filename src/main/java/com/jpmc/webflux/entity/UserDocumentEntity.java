package com.jpmc.webflux.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
@Data
@Setter
@Getter
@Table("user_documents")
public class UserDocumentEntity {

    @Id
    private int userId;
    private String userName;
    private int documentId;
    private String documentName;
}
