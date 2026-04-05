package com.huuphuoc.api.common.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@Builder
public class ResponEntity {
    private Object content;
    private boolean hasError;
    private List<String> errors;
    private LocalDate localDate;
    private int status;

}
