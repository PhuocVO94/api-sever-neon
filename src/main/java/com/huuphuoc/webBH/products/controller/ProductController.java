package com.huuphuoc.webBH.products.controller;


import com.huuphuoc.webBH.common.url.ResponseUtility;
import com.huuphuoc.webBH.products.dto.ProductDTOForSave;
import com.huuphuoc.webBH.products.dto.ProductDTOForUpdate;
import com.huuphuoc.webBH.products.model.Product;
import com.huuphuoc.webBH.products.service.ProductService;
import com.huuphuoc.webBH.products.util.ProductUtilConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(ProductUtilConfig.PRODUCT)
public class ProductController {


    ProductService productService;
    @Autowired
    private ResponseUtility responseEntity;

    public ProductController(ProductService productService, ResponseUtility responseUtility) {
        this.productService = productService;
        this.responseEntity = responseUtility;

    }

    @PostMapping(ProductUtilConfig.PRODUCT_SAVE)
    public Object save(@RequestBody ProductDTOForSave productDTOForSave) {

        return responseEntity.Get(productService.save(new Product(productDTOForSave.getName(),
                productDTOForSave.getDescription(),
                productDTOForSave.getPrice(),
                productDTOForSave.getImage())), HttpStatus.OK);
    }

    @GetMapping(ProductUtilConfig.PRODUCT_GETALL)
    public Object getAllProducts() {
        return responseEntity.Get(productService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("deleted/{id}")
    public Object deleteByID(@PathVariable UUID id) {
        productService.deleteById(id);
        return responseEntity.Get("Deleted successfully", HttpStatus.OK);
    }

    @PutMapping("update")
    public Object update(@RequestBody ProductDTOForUpdate productDTOForUpdate) {
        return responseEntity.Get(productService.updateProduct(productDTOForUpdate), HttpStatus.OK);
    }
}
