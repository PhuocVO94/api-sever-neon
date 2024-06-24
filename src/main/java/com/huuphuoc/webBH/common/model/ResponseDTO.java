package com.huuphuoc.webBH.common.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Getter
@Builder
public class ResponseDTO {


    private Object content;
    private boolean hasError;
    private List<String> errors;
    private String time;
    private int status;

}
