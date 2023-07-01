package com.ki.commom;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class ResultException extends RuntimeException {

    private int code;
    private String msg;

    public ResultException(String msg) {
        this.msg = msg;
        this.code = 1001;
    }

    public ResultException(int code, String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }
    public ResultException(int code){

        this.code = code;
        this.msg = "响应失败";
    }


}
