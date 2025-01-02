package org.vdt.productmanagementservice.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ApiResponseWrapper <T>{
    private T data;
    private String message;
    private Integer code;
}
