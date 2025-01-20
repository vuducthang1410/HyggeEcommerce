package org.vdt.productmanagementservice.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ResponseCode {
    ERR_CTG_CREATE("category.create.error"),
    SUCCESS_CTG_CREATE("category.create.successful"),
    ERR_CTG_NOT_FOUND("category.find.notfound"),
    SUCCESS_CTG_DELETE("category.delete.successful"),
    ERR_INPUT_VALIDATED("validated.failure"),
    SUCCESS_CTG_FOUND("category.find.successful"),
    SERVER_ERROR("server.error"),
    PAGE_SIZE_MIN("page.size.min"),
    PAGE_NUMBER_MIN("page.number.min"),
    SUCCESS_ACC_CREATE("acc.create.successful"),
    ;
    private String code;
}

