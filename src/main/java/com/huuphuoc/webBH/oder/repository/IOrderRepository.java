package com.huuphuoc.webBH.oder.repository;


import com.huuphuoc.webBH.oder.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Component
@Repository
public interface IOrderRepository extends JpaRepository<Order, UUID> {

    Order save(Order oder);


}
