package com.huuphuoc.webBH.products.dto;

import com.huuphuoc.webBH.oder.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductDTO {

    private UUID Base_UUID;
    private String name;
    private String description;
    private String price;
    private String image;
    private Set<Order> orders = new HashSet<>();


}
