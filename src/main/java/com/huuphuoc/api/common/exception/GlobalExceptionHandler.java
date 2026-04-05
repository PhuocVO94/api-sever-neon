package com.huuphuoc.api.common.exception;




import com.huuphuoc.api.common.model.ResponEntity;
import com.huuphuoc.api.common.utils.ResponseUtility;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.config.ConfigDataNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResponEntity> handleGlobalExtoption(Exception ex) {

        return new ResponseEntity<>(ResponEntity.builder()
                .content(ex.getMessage())
                .localDate(LocalDate.now())
                .hasError(true)
                .build(),HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ResponEntity> handleGlobalDataNotFundException(DataNotFoundException dataNotFoundException){

        return new ResponseEntity<>(ResponEntity.builder()
                .content(dataNotFoundException.getMessage())
                .hasError(true)
                .localDate(LocalDate.now())
                .build(),HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(AppExeption.class)
    public ResponseEntity<ResponEntity> handlerGlobalAppExeption(AppExeption appExeption){
    HttpStatus httpStatus = appExeption.getHttpStatus();
        return new ResponseEntity<>(ResponEntity.builder()
                .content(appExeption.getMessage())
                .hasError(true)
                .localDate(LocalDate.now())
                .build(), httpStatus);
    }




}
