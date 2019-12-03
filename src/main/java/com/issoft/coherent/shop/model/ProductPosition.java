package com.issoft.coherent.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
@AllArgsConstructor
public class ProductPosition {

    @DBRef
    private Product product;

    private int amount;
}
