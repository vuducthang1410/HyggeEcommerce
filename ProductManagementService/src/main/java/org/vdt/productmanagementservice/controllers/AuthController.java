package org.vdt.productmanagementservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.PreDestroy;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vdt.productmanagementservice.dtos.request.AccountRegisterRqDto;
import org.vdt.productmanagementservice.dtos.response.ApiResponseWrapper;
import org.vdt.productmanagementservice.handlers.AuthHandler;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthHandler authHandler;

    @Operation
    @PostMapping("/register")
    public ResponseEntity<ApiResponseWrapper<Object>> registerUser(@RequestBody @Valid AccountRegisterRqDto accountRegisterRqDto,
                                                                   BindingResult bindingResult,
                                                                   @RequestHeader(name = "transactionId") String transactionId) {
        return new ResponseEntity<>(
                authHandler.handlerRegister(accountRegisterRqDto, bindingResult, transactionId),
                HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponseWrapper<Object>>  login(@RequestBody @Valid AccountRegisterRqDto accountRegisterRqDto,
                        BindingResult bindingResult,
                        @RequestHeader(name = "transactionId") String transactionId) {
        return new ResponseEntity<>(authHandler.handlerLogin(accountRegisterRqDto, bindingResult, transactionId), HttpStatus.OK);
    }
    @PostMapping("/log-out")
    public String logout() {
        return "";
    }
}
