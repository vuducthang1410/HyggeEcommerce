package org.vdt.productmanagementservice.handlers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.vdt.productmanagementservice.common.Constant;
import org.vdt.productmanagementservice.common.ResponseCode;
import org.vdt.productmanagementservice.common.Util;
import org.vdt.productmanagementservice.dtos.request.AccountRegisterRqDto;
import org.vdt.productmanagementservice.dtos.response.ApiResponseWrapper;
import org.vdt.productmanagementservice.entities.User;
import org.vdt.productmanagementservice.services.IAuthService;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthHandler {
    private final Logger logger = LoggerFactory.getLogger(AuthHandler.class);
    private final Util util;
    private final IAuthService authService;
    public ApiResponseWrapper<Object> handlerRegister(AccountRegisterRqDto accountRegisterRqDto,
                                                      BindingResult bindingResult,
                                                      String transactionId) {
        ApiResponseWrapper<Object> apiResponseWrapper = new ApiResponseWrapper<>();
        if (bindingResult.hasErrors()) {
            apiResponseWrapper.setCode(HttpStatus.BAD_REQUEST.value());
            apiResponseWrapper
                    .setMessage(util.getMessageFromMessageSource(ResponseCode.ERR_INPUT_VALIDATED.getCode()));
            return apiResponseWrapper;
        }
        try{
            User user=authService.register(accountRegisterRqDto);
            if(user!=null){
                apiResponseWrapper.setCode(HttpStatus.OK.value());
                apiResponseWrapper.setData(user.getId());
                apiResponseWrapper.setMessage(util.getMessageFromMessageSource(ResponseCode.SUCCESS_ACC_CREATE.getCode()));
                return apiResponseWrapper;
            }
        } catch (Exception e) {
            logger.error(Constant.MESSAGE_LOG,
                    transactionId, "An error occurred while get all category",
                    e.getMessage()
            );
            apiResponseWrapper.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            apiResponseWrapper.setMessage(util.getMessageFromMessageSource(ResponseCode.SERVER_ERROR.getCode()));
        }
        return apiResponseWrapper;
    }

    public ApiResponseWrapper<Object> handlerLogin(@Valid AccountRegisterRqDto accountRegisterRqDto,
                                                   BindingResult bindingResult,
                                                   String transactionId) {
        ApiResponseWrapper<Object> apiResponseWrapper = new ApiResponseWrapper<>();
        if (bindingResult.hasErrors()) {
            apiResponseWrapper.setCode(HttpStatus.BAD_REQUEST.value());
            apiResponseWrapper
                    .setMessage(util.getMessageFromMessageSource(ResponseCode.ERR_INPUT_VALIDATED.getCode()));
            return apiResponseWrapper;
        }
        try{
            Map<String,Object> dataResponse =authService.login(accountRegisterRqDto);
            apiResponseWrapper.setCode(HttpStatus.OK.value());
            apiResponseWrapper.setData(dataResponse);
            apiResponseWrapper.setMessage("Login successful");
        } catch (Exception e) {
            logger.error(Constant.MESSAGE_LOG,
                    transactionId, "An error occurred while get all category",
                    e.getMessage()
            );
            apiResponseWrapper.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            apiResponseWrapper.setMessage(util.getMessageFromMessageSource(ResponseCode.SERVER_ERROR.getCode()));
        }
        return apiResponseWrapper;
    }
}
