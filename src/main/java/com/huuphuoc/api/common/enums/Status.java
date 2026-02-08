package com.huuphuoc.api.common.enums;

public enum Status {
    ACTIVE,         // Đã kích hoạt, được phép đăng nhập
    UNVERIFIED,     // Mới tạo, chưa kích hoạt email (hoặc PENDING)
    BANNED          // Bị Admin khóa do vi phạm

}
