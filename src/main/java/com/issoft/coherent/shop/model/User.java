package com.issoft.coherent.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@Document
public class User {

    @Id
    String id;
    String username;

    @JsonIgnore
    String password;

    List<Role> role;

}
