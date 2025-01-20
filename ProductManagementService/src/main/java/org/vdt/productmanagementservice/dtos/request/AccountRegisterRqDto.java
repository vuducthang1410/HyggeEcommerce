package org.vdt.productmanagementservice.dtos.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRegisterRqDto {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}
