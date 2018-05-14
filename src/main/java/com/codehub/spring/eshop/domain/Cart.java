package com.codehub.spring.eshop.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * Created by Dimitris on 14/5/2018.
 */

@Entity
@Table(name = "cart")
@Data
public class Cart {

    private int userId;

    private int productId;

    private BigDecimal price;

    private BigDecimal tax;

    private BigDecimal quantity;

    private Instant dateAdded;
}
