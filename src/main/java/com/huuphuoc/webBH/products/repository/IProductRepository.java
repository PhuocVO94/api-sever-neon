package com.huuphuoc.webBH.products.repository;

import com.huuphuoc.webBH.products.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
@Component
public interface IProductRepository extends JpaRepository<Product, UUID> {


}
