package org.vdt.productmanagementservice.handlers;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.vdt.productmanagementservice.common.Constant;
import org.vdt.productmanagementservice.common.ResponseCode;
import org.vdt.productmanagementservice.common.Util;
import org.vdt.productmanagementservice.dtos.request.CategoryRqDto;
import org.vdt.productmanagementservice.dtos.request.CategoryUpdateRqDto;
import org.vdt.productmanagementservice.dtos.response.ApiResponseWrapper;
import org.vdt.productmanagementservice.dtos.response.CategoryRpDto;
import org.vdt.productmanagementservice.entities.Category;
import org.vdt.productmanagementservice.services.ICategoryService;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryHandler {
    private final ICategoryService categoryService;
    private final Util util;
    private final Logger logger = LoggerFactory.getLogger(CategoryHandler.class);

    public ApiResponseWrapper<Object> addCategory(CategoryRqDto categoryRqDto,
                                                  MultipartFile imageCategory,
                                                  BindingResult bindingResult,
                                                  String transactionId) {
        ApiResponseWrapper<Object> apiResponseWrapper = new ApiResponseWrapper<>();
        if (bindingResult.hasErrors()) {
            apiResponseWrapper.setCode(HttpStatus.BAD_REQUEST.value());
            apiResponseWrapper
                    .setMessage(util.getMessageFromMessageSource(ResponseCode.ERR_INPUT_VALIDATED.getCode()));
            return apiResponseWrapper;
        }
        try {
            Category category = categoryService.addCategory(categoryRqDto, imageCategory, transactionId);
            if (category == null) {
                apiResponseWrapper.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
                apiResponseWrapper.setMessage(util.getMessageFromMessageSource(ResponseCode.SERVER_ERROR.getCode()));
                return apiResponseWrapper;
            }
            apiResponseWrapper.setCode(HttpStatus.CREATED.value());
            apiResponseWrapper.setMessage(util.getMessageFromMessageSource(ResponseCode.SUCCESS_CTG_CREATE.getCode()));
            apiResponseWrapper.setData(category);
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

    public ApiResponseWrapper<Object> findByIdHandle(String id, String transactionId) {
        ApiResponseWrapper<Object> apiResponseWrapper = new ApiResponseWrapper<>();
        if (!StringUtils.hasText(id)) {
            apiResponseWrapper.setCode(HttpStatus.BAD_REQUEST.value());
            apiResponseWrapper.setMessage(util.getMessageFromMessageSource(ResponseCode.ERR_INPUT_VALIDATED.getCode()));
            return apiResponseWrapper;
        }
        try {
            CategoryRpDto categoryRqDto = categoryService.findById(id, transactionId);
            apiResponseWrapper.setCode(HttpStatus.OK.value());
            apiResponseWrapper.setData(categoryRqDto);
            if (categoryRqDto == null) {
                apiResponseWrapper.setMessage("Category not found.");
            } else {
                apiResponseWrapper.setMessage("Category found.");
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

    public ApiResponseWrapper<Object> deleteById(String id, String transactionId) {
        ApiResponseWrapper<Object> apiResponseWrapper = new ApiResponseWrapper<>();
        if (!StringUtils.hasText(id)) {
            apiResponseWrapper.setCode(HttpStatus.BAD_REQUEST.value());
            apiResponseWrapper.setMessage(util.getMessageFromMessageSource(ResponseCode.ERR_INPUT_VALIDATED.getCode()));
            return apiResponseWrapper;
        }
        try {
            apiResponseWrapper.setCode(HttpStatus.OK.value());
            String messageResponse = categoryService.deleteCategoryById(id, transactionId) ?
                    util.getMessageFromMessageSource(ResponseCode.SUCCESS_CTG_DELETE.getCode()) :
                    util.getMessageFromMessageSource(ResponseCode.ERR_CTG_NOT_FOUND.getCode());
            apiResponseWrapper.setMessage(messageResponse);
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

    public ApiResponseWrapper<Object> getAllCategory(Integer pageNo, Integer pageSize, String transactionId) {
        ApiResponseWrapper<Object> apiResponseWrapper = new ApiResponseWrapper<>();
        if (pageNo < 0 || pageSize < 1) {
            apiResponseWrapper.setCode(HttpStatus.BAD_REQUEST.value());
            String messageResponse=pageNo < 0 ? util.getMessageFromMessageSource(ResponseCode.PAGE_NUMBER_MIN.getCode()) :
                    util.getMessageFromMessageSource(ResponseCode.PAGE_SIZE_MIN.getCode());
            apiResponseWrapper.setMessage(messageResponse);
            return apiResponseWrapper;
        }
        try {
            Map<String, Object> dataResponse = categoryService.findAllCategories(pageNo, pageSize, transactionId);
            Optional<Long> totalRecord = Optional.ofNullable((Long) dataResponse.get("totalRecords"));
            if (totalRecord.isEmpty()) {
                throw new Exception("Error while getting category list from database");
            } else if (totalRecord.get() == 0) {
                apiResponseWrapper.setMessage(util.getMessageFromMessageSource(ResponseCode.ERR_CTG_NOT_FOUND.getCode()));
            } else {
                apiResponseWrapper.setMessage(ResponseCode.SUCCESS_CTG_FOUND.getCode());
            }
            apiResponseWrapper.setCode(HttpStatus.OK.value());
            apiResponseWrapper.setData(dataResponse);
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

    public ApiResponseWrapper<Object> handlerUpdate(String id,
                                                    CategoryUpdateRqDto categoryUpdateRqDto,
                                                    String transactionId,
                                                    BindingResult bindingResult) {
        ApiResponseWrapper<Object> apiResponseWrapper = new ApiResponseWrapper<>();
        if (bindingResult.hasErrors()) {
            apiResponseWrapper.setCode(HttpStatus.BAD_REQUEST.value());
            apiResponseWrapper
                    .setMessage(util.getMessageFromMessageSource(ResponseCode.ERR_INPUT_VALIDATED.getCode()));
            return apiResponseWrapper;
        }
        try {
            CategoryRpDto categoryRpDto=categoryService.updateCategory(categoryUpdateRqDto,id);
            if(categoryRpDto==null) {
                apiResponseWrapper.setCode(HttpStatus.BAD_REQUEST.value());
                apiResponseWrapper.setMessage(util.getMessageFromMessageSource(ResponseCode.ERR_CTG_NOT_FOUND.getCode()));
            }else {
                apiResponseWrapper.setCode(HttpStatus.OK.value());
                apiResponseWrapper.setData(categoryRpDto);
                apiResponseWrapper.setMessage("Category updated successfully.");
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
}
