package com.root.idempotent.config;

import com.root.idempotent.common.Result;
import com.root.idempotent.enums.CommonEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @author qch
 * @date 2021年11月09日 3:05 下午
 * 自定义异常统一处理器
 */
@ControllerAdvice
public class MyExceptionHandler{

    @ExceptionHandler({Exception.class})
    public Result exceptionHandler(HttpServletRequest req,Exception e){
        return Result.instance(CommonEnum.ERROR.getCode(),e.getMessage());
    }

}
