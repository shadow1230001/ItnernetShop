package com.issoft.coherent.shop.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@NoArgsConstructor
@Document
public class Basket {

    @Id
    String id;

    Map<String, ProductPosition> productPositions;
}
