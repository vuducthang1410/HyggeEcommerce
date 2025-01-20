package org.vdt.productmanagementservice.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountInfoDtoRq {
    private String firstName;
    private String lastName;
    private String email;
    private Timestamp dateOfBirth;
}
