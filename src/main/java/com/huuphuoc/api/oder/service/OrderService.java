package com.huuphuoc.api.oder.service;


import com.huuphuoc.api.oder.repository.IOrderRepository;
import com.huuphuoc.api.user.repository.IUserRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {


    IUserRepository iUserRepository;
    IOrderRepository iOderRepository;

    public OrderService(IOrderRepository iOderRepository, IUserRepository iUserRepository) {
        this.iOderRepository = iOderRepository;
        this.iUserRepository = iUserRepository;
    }


}
