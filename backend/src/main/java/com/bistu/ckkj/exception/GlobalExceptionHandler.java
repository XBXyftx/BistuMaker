package com.bistu.ckkj.exception;


import com.bistu.ckkj.pojo.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/**
 * 全局异常处理器，用于捕获Controller层抛出的异常，并返回统一格式的异常信息。
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 处理所有未被其他异常处理器捕获的异常。
     *
     * @param e 抛出的异常对象
     * @return 返回一个表示异常信息的结果对象
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e){
        e.printStackTrace();
        return Result.error(StringUtils.hasLength(e.getMessage())? e.getMessage() : "操作失败");
    }
}
