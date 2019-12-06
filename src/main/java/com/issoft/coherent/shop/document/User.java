package com.issoft.coherent.shop.document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.issoft.coherent.shop.model.Role;
import com.issoft.coherent.shop.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@Document
@Builder
@NoArgsConstructor
public class User {

    @Id
    String id;

    @Indexed(unique = true)
    String username;

    @JsonIgnore
    String password;

    List<Role> roles;

    boolean active;

    Status statuses;

    @CreatedDate
    LocalDateTime created;

    String confirm; //key

}
