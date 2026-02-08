package com.huuphuoc.api.products.service;


import com.huuphuoc.api.config.ModelMapperConfig;
import com.huuphuoc.api.common.exception.GlobalRuntimeException;
import com.huuphuoc.api.products.dto.ProductDTO;
import com.huuphuoc.api.products.dto.ProductDTOForUpdate;
import com.huuphuoc.api.products.model.Product;
import com.huuphuoc.api.products.repository.IProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    IProductRepository productRepository;
    ModelMapperConfig modelMapperConfig;


    public ProductServiceImpl(IProductRepository productRepository) {
        this.productRepository = productRepository;
        this.modelMapperConfig = new ModelMapperConfig();
    }

    @Override
    public ModelMapper modelMapper() {
        return modelMapperConfig;
    }

    @Override
    public Product findByID(UUID uuid) {
        return null;
    }


    @Override
    public JpaRepository<Product, UUID> getRepository() {
        return this.productRepository;
    }


    public Product save(Product product) {
        return this.productRepository.save(product);

    }

    @Override
    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream().map(product -> {
            return modelMapperConfig.map(product, ProductDTO.class);

        }).toList();
    }


    @Override
    public Product updateProduct(ProductDTOForUpdate productDTOForUpdate) {
        Optional<Product> optionalProduct = productRepository.findById(productDTOForUpdate.getBase_UUID());
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
//            System.out.println("Vo Huu Phuoc Designer");
            System.out.println(product);

            product.setName(productDTOForUpdate.getName());
            product.setDescription(productDTOForUpdate.getDescription());
            product.setImage(productDTOForUpdate.getImage());
            product.setPrice(productDTOForUpdate.getPrice());
            // Lưu và trả về sản phẩm đã cập nhật
            return productRepository.save(product);
        } else {

            throw new GlobalRuntimeException("Product not found");
        }

    }
}


