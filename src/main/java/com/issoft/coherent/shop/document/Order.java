package com.issoft.coherent.shop.document;

import com.issoft.coherent.shop.model.OrderForm;
import com.issoft.coherent.shop.model.ProductPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;
import java.util.Date;

@Data
@AllArgsConstructor
@Document
@Builder
public class Order {

    @Id
    private String id;

    private Collection<ProductPosition> productPositions;

    @DBRef
    private User user;

    private Double total;

    private boolean completed;

    private OrderForm orderForm;

    private Date created;
}
