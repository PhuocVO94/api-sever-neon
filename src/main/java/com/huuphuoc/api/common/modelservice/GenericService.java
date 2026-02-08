package com.huuphuoc.webBH.common.modelservice;

import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface GenericService<T, D, ID extends UUID> {


    JpaRepository<T, UUID> getRepository();

    /* Đinj nghĩa 1 lớp model mapper ở đây dùng để khi các class khác impl Generic thì sẽ khởi tạo 1 modelMapper ta khỏi cần phải đi khỏi tạo*/
    ModelMapper modelMapper();

    T findByID(ID id);


    default List<D> findAll() {

        return modelMapper().map(getRepository().findAll(), List.class);
    }

    default T save(T entity) {
        return getRepository().save(entity);

    }

    default T findById(ID id) {

        return getRepository().findById(id).orElse(null);
    }

    default void delete(T entity) {

        getRepository().delete(entity);
    }

    default void deleteById(ID id) {

        getRepository().deleteById(id);
    }

}
