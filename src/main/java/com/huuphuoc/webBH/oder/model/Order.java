package com.huuphuoc.webBH.oder.model;


import com.huuphuoc.webBH.common.model.BaseEntity;
import com.huuphuoc.webBH.products.model.Product;
import com.huuphuoc.webBH.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = OrderEntity.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
public class Order extends BaseEntity {
    /* ManyToOne dùng để đánh dâu cho chúng ta biết lag user là thuộc tính khoá ngoại trong bản Oder*/

    @ManyToOne
    @JoinColumn(name = OrderEntity.OderUser.USERID)
    private User user;
    @ManyToMany
    @JoinTable(name = OrderEntity.OderProduct.TABLE_NAME,
            joinColumns = @JoinColumn(name = OrderEntity.OderProduct.ODERID),
            inverseJoinColumns = @JoinColumn(name = OrderEntity.OderProduct.PRODUCTID)
    )
    private Set<Product> products = new HashSet<>();


}
