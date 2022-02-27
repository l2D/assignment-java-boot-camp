package com.l2d.week1.product.model;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter @Setter @NoArgsConstructor
public class Product {
    
    // @Id  @Column(name = "uid", nullable = false, unique = true, updatable = false)
    // private UUID uid = UUID.randomUUID();

    @Id
    private Integer id;

    @Column(name = "merchant_uid", nullable = true)
    private UUID merchantUid;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "stock", nullable = false)
    private int stock;

}
