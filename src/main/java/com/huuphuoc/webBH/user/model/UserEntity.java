package com.huuphuoc.webBH.user.model;


import lombok.experimental.UtilityClass;


/*Đánh Dấu @UitlityClass
* Nhằm đảm bảo rằng  và không mapping đến DB.
* Nhămg tạo ra class như vậy dùng để Khả năng mở rộng của dự án.
* */
@UtilityClass
public class UserEntity {

    @UtilityClass
    public  class user {
        public final  static  String TABLE_NAME = "w_user";
        public final  static  String COLUMN_NAME = "userName";
        public final  static  String COLUMN_PASSWORD = "password";
        public final  static  String COLUMN_PHONE_NUMBER = "phone_number";
        public final  static  String COLUMN_EMAIL = "e_mail";

    }
}
