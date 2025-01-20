package org.vdt.productmanagementservice.services;

import org.vdt.productmanagementservice.dtos.request.AccountRegisterRqDto;
import org.vdt.productmanagementservice.entities.User;

import java.util.Map;

public interface IAuthService {
    Map<String, Object> login(AccountRegisterRqDto accountRegisterRqDto);
    User register(AccountRegisterRqDto accountRegisterRqDto);
    Map<String, Object> updateInfo(String userId);
    String logout(String userId);
}
