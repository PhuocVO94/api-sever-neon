package com.huuphuoc.api.oder.repository;


import com.huuphuoc.api.oder.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Component
@Repository
public interface IOrderRepository extends JpaRepository<Order, UUID> {

    Order save(Order oder);


}
