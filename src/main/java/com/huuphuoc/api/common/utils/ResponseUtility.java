package com.huuphuoc.api.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.huuphuoc.api.common.model.ResponEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;


@Component
@JsonInclude(NON_NULL)
public class ResponseUtility {
    public ResponseEntity<ResponEntity> Get(Object result, HttpStatus status) {
        return new org.springframework.http.ResponseEntity<>(
                ResponEntity.builder()
                        .hasError(false)
                        .content(result)
                        .localDate(LocalDate.now())
                        .status(status.value())
                        .build(), status
        );
    }


    public ResponseEntity<ResponEntity> Error(Object result, HttpStatus status) {
        return new org.springframework.http.ResponseEntity<>(
                        ResponEntity.builder().content(result)
                        .hasError(true)
                        .localDate(LocalDate.now())
                        .status(status.value())
                        .build(), status
        );
    }
}



