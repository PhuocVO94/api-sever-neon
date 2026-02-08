package com.huuphuoc.api.products.service;

import com.huuphuoc.api.common.modelservice.GenericService;
import com.huuphuoc.api.products.dto.ProductDTO;
import com.huuphuoc.api.products.dto.ProductDTOForUpdate;
import com.huuphuoc.api.products.model.Product;

import java.util.UUID;

public interface ProductService extends GenericService<Product, ProductDTO, UUID> {

    Product updateProduct(ProductDTOForUpdate productDTOForUpdate);
}
