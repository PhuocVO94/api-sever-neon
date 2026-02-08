package com.huuphuoc.webBH.oder.model;


import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderEntity {


    public final static String TABLE_NAME = "w_oder";

    /*Class Oder User dùng để đinh nghĩa các giá trị trong bản phụ Oder*/
    public class OderUser {

        public final static String USERID = "user_id";

    }

    public class OderProduct {

        public final static String TABLE_NAME = "oder_product";
        public final static String ODERID = "oder_id";
        public final static String PRODUCTID = "product_id";
    }
}
