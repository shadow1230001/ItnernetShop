package com.issoft.coherent.shop.document;

import com.issoft.coherent.shop.model.ProductPosition;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Map;

@Data
@NoArgsConstructor
@Document
public class Basket {

    @Id
    String id;

    Map<String, ProductPosition> productPositions;

    Date created;
}
