package com.huuphuoc.api.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huuphuoc.api.common.utils.DateTimeFomat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.experimental.UtilityClass;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener; // Import đúng cái này
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime; // Khuyên dùng cái này thay vì String
import java.util.UUID;

@Data  /* Dùng để tạo Getter, Setter, toString... */
@NoArgsConstructor /* Dùng để tạo Constructor không tham số */
@AllArgsConstructor /* Dùng để tạo Constructor đầy đủ tham số */
@SuperBuilder /* Dùng để cho các lớp con sử dụng Builder pattern */
@MappedSuperclass /* Dùng để các lớp con kế thừa các cột này vào DB của chính nó */
// SỬA 1: Dùng AuditingEntityListener để tự động điền ngày giờ, KHÔNG dùng AutoCloseable
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = columns.ID)
    public UUID id; // Nên đặt tên là 'id' theo chuẩn chung, tránh dùng Base_UUID

    @Version
    @Column(name = columns.VERSION)
    private long version; // SỬA 2: Đã xóa 'static' (Mỗi đối tượng phải có version riêng)

    @CreatedBy
    @Column(name = columns.CREATED_BY)
    private String createdBy; // SỬA 2: Đã xóa 'static'

    @CreatedDate
    @DateTimeFormat(pattern = DateTimeFomat.DATETIME_FOMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeFomat.DATETIME_FOMAT)
    @Column(name = columns.CREATED_AT)
    // SỬA 2: Xóa 'static' & SỬA 3: Đổi String sang LocalDateTime (Chuẩn nhất cho ngày giờ)
    private LocalDateTime createdAt;

    @LastModifiedBy
    @Column(name = columns.LASTMODIFIED_BY)
    private String lastModifiedBy; // SỬA 2: Đã xóa 'static'

    @LastModifiedDate
    @DateTimeFormat(pattern = DateTimeFomat.DATETIME_FOMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeFomat.DATETIME_FOMAT)
    @Column(name = columns.LASTMODIFIED_AT)
    // SỬA 2: Xóa 'static' & SỬA 3: Đổi sang LocalDateTime
    private LocalDateTime lastModifiedAt;

    @UtilityClass
    public static class columns {
        public static final String ID = "ID";
        public static final String VERSION = "version";
        public static final String CREATED_BY = "createdBy";
        public static final String CREATED_AT = "createdAt";
        public static final String LASTMODIFIED_BY = "lastModifiedBy";
        public static final String LASTMODIFIED_AT = "lastModifiedAt";
    }
}