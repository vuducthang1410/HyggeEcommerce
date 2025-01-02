package org.vdt.productmanagementservice.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ResponseCode {

    ERR_PRD_CREATE("ERR_PRS_0001","Execute error when create new product");
    private String code;
    private String desc;
}

