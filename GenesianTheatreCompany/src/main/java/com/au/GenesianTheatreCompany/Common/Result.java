package com.au.GenesianTheatreCompany.Common;

import lombok.Data;

@Data
public class Result {
    private int code; // status code
    private String msg; // return message
    private Long total;  // total number
    private Object data; // data
    private boolean success;

    public static Result fail(){
        return result(400, "fail", 0L, null);
    }
    public static Result fail(Object data){
        return result(400, "fail", 0L, data);
    }
    public static Result suc(){
        return result(200, "successful", 0L, null);
    }
    public static Result suc(Object data){
        return result(200, "successful", 0L, data);
    }
    public static Result suc(Object data, Long total){
        return result(200, "successful", total, data);
    }

    public static Result customize(int code, String msg, Long total, Object data){
        return result(code, msg, total, data);
    }
    private static Result result(int code, String msg, Long total, Object data){

        Result res = new Result();
        res.setData(data);
        res.setMsg(msg);
        res.setCode(code);
        res.setTotal(total);
        return res;

    }

    public boolean isSuccess() {
        return success;
    }
}