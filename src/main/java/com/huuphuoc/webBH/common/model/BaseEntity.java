package com.huuphuoc.webBH.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huuphuoc.webBH.common.url.DateTimeFomat;
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
import org.springframework.format.annotation.DateTimeFormat;

import java.util.UUID;

@Data  /* Dùng Để tạo Hàm Seter Getter*/
@NoArgsConstructor/*Dùng để tạo Contructor khồng tham số*/
@AllArgsConstructor /*Dùng để tạo All Contructot*/
@SuperBuilder /*Dùng Để cho Các lớp con sử dụng xư*/
@MappedSuperclass /*  Dùng để cho Các Lớp Con ánh Xạ tới db của tầng lớp   */
@EntityListeners(AutoCloseable.class)/*Dùng để khởi tạo đối tượng*/

public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = columns.ID)
    public UUID Base_UUID;


    /*
     * Nhămg cho mọi người biết hiện tại đang ở version bao nhiêu, và ai là người tạo ra.
     * tạo ra vời thời gian nào.
     * Người thay đổi lần cuối.
     * Trong spring boot Hỗ trợ hết các Annotation Để tạo ra 1 Class format.
     *  @Version,   @CreatedBy,  @CreatedBy, @CreatedDate,  @LastModifiedBy,  @LastModifiedDate
     * */

    @Version
    @Column(name = columns.VERSION)
    public long version;
    //
    @CreatedBy
    @Column(name = columns.CREATED_BY)
    public String createdBy;


    @CreatedDate
    @DateTimeFormat(pattern = DateTimeFomat.DATETIME_FOMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeFomat.DATETIME_FOMAT)
    @Column(name = columns.CREATED_AT)
    public String createdAt;

    @LastModifiedBy
    @Column(name = columns.LASTMODIFIED_BY)
    public String lastModifiedBy;

    @LastModifiedDate
    @CreatedDate
    @DateTimeFormat(pattern = DateTimeFomat.DATETIME_FOMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeFomat.DATETIME_FOMAT)
    @Column(name = columns.LASTMODIFIED_AT)
    public String lastModifiedAt;


    /*   Tạo ra 1 class Utility Class
    *     Nhằm để mapping với các cộ column trong bảng.
        Để CLass trên không mapping xuống database ta cần đánh dấu Annotation @UtilityClas
    * Khi ta đánh dấu @Annotation trên thì Springboot nó không mapping xuống database

    * */
    @UtilityClass
    static class columns {
        final String ID = "ID";
        final String VERSION = "version";
        final String CREATED_BY = "createdBy";
        final String CREATED_AT = "createdAt";
        final String LASTMODIFIED_BY = "lastModifiedBy";
        final String LASTMODIFIED_AT = "lastModifiedAt";

    }

}
