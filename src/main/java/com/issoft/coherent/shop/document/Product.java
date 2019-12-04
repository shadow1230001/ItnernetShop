package com.issoft.coherent.shop.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document
@Builder
public class Product {

    @Id
    private String id;

    private String name;

    private String description;

    private Double price;

}

