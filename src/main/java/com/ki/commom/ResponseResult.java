package com.ki.commom;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ResponseResult<T> implements Serializable {

    /** 状态码 */
    private int code ;
    /** 提示信息 */
    private String msg ;

    /** 数据 */
    private T data;



    public ResponseResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private ResponseResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseResult(T data) {

        this.data = data;
    }
    public boolean isSUCCESS(){


        return  true;
    }




    public  static  <T>ResponseResult SUCCESS(T data){


        return new  ResponseResult<T>(1000,"响应成功",data);
    }

    public  static  ResponseResult SUCCESS(){

        return new  ResponseResult(1000,"响应成功");
    }

    public  static  ResponseResult FAIL(){


        return new  ResponseResult(1001,"响应失败");
    }

    public  static  ResponseResult FAIL(String msg){


        return new  ResponseResult(1001,msg);
    }

    public  static  ResponseResult FAIL(int code,String msg){


        return new  ResponseResult(code,msg);
    }


}
