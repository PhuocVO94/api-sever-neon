package com.huuphuoc.webBH.products.model;


import com.huuphuoc.webBH.common.model.BaseEntity;
import com.huuphuoc.webBH.oder.model.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = ProductUtil.PRODUCT_TABLE)
public class Product extends BaseEntity {

    @Column(name = ProductUtil.PRODUCT_NAME)
    private String name;

    @Column(name = ProductUtil.PRODUCT_DESCRIPTION)
    private String description;

    @Column(name = ProductUtil.PRODUCT_PRICE)
    private String price;
    @Column(name = ProductUtil.PRODUCT_IMAGE)
    private String image;


    @ManyToMany(mappedBy = "products", cascade = {CascadeType.ALL})
    private Set<Order> orders = new HashSet<>();

    public Product(String name, String description, String price, String image) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
    }
}
