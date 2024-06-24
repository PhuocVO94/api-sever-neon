package com.huuphuoc.webBH.common.exception;


import com.huuphuoc.webBH.common.model.ResponseDTO;
import com.huuphuoc.webBH.common.url.ResponseUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final ResponseUtility responseUtility;

    @Autowired
    public GlobalExceptionHandler(ResponseUtility responseUtility) {
        this.responseUtility = responseUtility;
    }

    /* ACheck Lỗi Check lỗi runthimeException các giá trị các giá trị*/
    @ExceptionHandler(GlobalRuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseDTO> handlerHttpMessageNotReadableException(GlobalRuntimeException ex) {
        return responseUtility.Error(ex, HttpStatus.BAD_REQUEST);

    }

    /* ACheck Lỗi validate các giá trị*/
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseDTO> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return responseUtility.ErrorMethodArgumentNotValidException(ex, HttpStatus.BAD_REQUEST);
    }

}
