package com.huuphuoc.webBH.common.url;

import com.huuphuoc.webBH.common.model.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.View;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Component
public class ResponseUtility {
    private final View error;

    public ResponseUtility(View error) {
        this.error = error;
    }


    /*Dùng lớp ResponseDTO  Dùng Để nhận giá trị
     * trả về từ Sever
     *
     *
     *
     *  */

    public ResponseEntity<ResponseDTO> Get(Object result, HttpStatus status) {
        return new org.springframework.http.ResponseEntity<>(
                ResponseDTO.builder()
                        .content(result)
                        .hasError(false)
                        .errors(null)
                        .time(DateTimeFomat.strDateTime)
                        .status(status.value())
                        .build(), status
        );
    }


    public ResponseEntity<ResponseDTO> Error(Exception exception, HttpStatus status) {
        return new ResponseEntity<>(ResponseDTO.builder().content(null)
                .hasError(true)
                .errors(List.of(exception.getMessage()))
                .status(status.value())
                .time(DateTimeFomat.strDateTime)
                .build(), status
        );

    }

    public ResponseEntity<ResponseDTO> ErrorMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpStatus status) {
        return new ResponseEntity(ResponseDTO.builder()
                .content(null)
                .hasError(true)
                .errors(
                        Arrays.stream(ex.getDetailMessageArguments())
                                .filter(Objects::nonNull)  // Loại bỏ các giá trị null
                                .map(Object::toString)     // Chuyển các đối tượng thành chuỗi
                                .filter(s -> !s.isEmpty()) // Loại bỏ các chuỗi rỗng
                                .collect(Collectors.toList())
                )
                .time(DateTimeFomat.strDateTime)
                .status(HttpStatus.BAD_REQUEST.value())
                .build(), status);
    }


}
