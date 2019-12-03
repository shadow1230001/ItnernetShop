package com.issoft.coherent.shop.document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.issoft.coherent.shop.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Document
@Builder
public class User {

    @Id
    String id;

    String username;

    @JsonIgnore
    String password;

    List<Role> roles;

    boolean active;

    Date created;
}