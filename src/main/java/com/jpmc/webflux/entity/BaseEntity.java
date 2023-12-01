package com.jpmc.webflux.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Setter
@Getter
public class BaseEntity {

    private String createdBy;
    private String updatedBy;
    private Date createdAt;
    private Date updatedAt;
}
