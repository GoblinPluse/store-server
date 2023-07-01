package com.ki.controller;

import java.io.Serializable;

public class Result implements Serializable {
    private String result ;
    private String desc ;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Result{" +
                "result='" + result + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
