package com.huuphuoc.webBH.products.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductDTOForSave {


    private String name;
    private String description;
    private String price;
    private String image;
}
