package com.huuphuoc.webBH.products.service;

import com.huuphuoc.webBH.common.modelservice.GenericService;
import com.huuphuoc.webBH.products.dto.ProductDTO;
import com.huuphuoc.webBH.products.dto.ProductDTOForUpdate;
import com.huuphuoc.webBH.products.model.Product;

import java.util.UUID;

public interface ProductService extends GenericService<Product, ProductDTO, UUID> {

    Product updateProduct(ProductDTOForUpdate productDTOForUpdate);
}
