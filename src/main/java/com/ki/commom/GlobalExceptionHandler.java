package com.ki.commom;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResultException.class)
    @ResponseBody
    public ResponseResult handle(ResultException e){
		log.error("业务处理异常:{}",e);

        return ResponseResult.FAIL(e.getCode(),e.getMsg());
    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult handle(Exception e){
		log.error("系统异常：{}",e);

		return ResponseResult.FAIL();
    }


}
