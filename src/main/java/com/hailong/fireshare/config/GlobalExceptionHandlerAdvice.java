package com.hailong.fireshare.config;

import com.hailong.fireshare.entity.RestResult;
import com.hailong.fireshare.entity.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandlerAdvice {

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    //空指针处理方法
    public RestResult error(NullPointerException e) {
        e.printStackTrace();
        log.error("全局异常捕获：" + e);
        return RestResult.setResult(ResultCodeEnum.NULL_POINT);
    }

    //下标越界处理方法
    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseBody
    public RestResult error(IndexOutOfBoundsException e) {
        e.printStackTrace();
        log.error("全局异常捕获：" + e);
        return RestResult.setResult(ResultCodeEnum.INDEX_OUT_OF_BOUNDS);
    }

}
