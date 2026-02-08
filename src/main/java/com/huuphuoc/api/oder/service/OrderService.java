package com.huuphuoc.webBH.oder.service;


import com.huuphuoc.webBH.oder.repository.IOrderRepository;
import com.huuphuoc.webBH.user.repository.IUserRepository;
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
