package com.huuphuoc.webBH.products.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTOForUpdate {

    private UUID Base_UUID;
    private String name;
    private String description;
    private String price;
    private String image;
}
