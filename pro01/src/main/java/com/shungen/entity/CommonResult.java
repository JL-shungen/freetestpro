package com.shungen.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class CommonResult<T> {

    private Integer code; //编码：1成功，0和其它数字为失败

    private String msg; //错误信息

    private T data; //数据

    private Map map = new HashMap(); //动态数据

    public static <T> CommonResult<T> success(T object) {
        CommonResult<T> r = new CommonResult<T>();
        r.data = object;
        r.code = 1;
        return r;
    }

    public static <T> CommonResult<T> error(Integer errCode, String msg) {
        CommonResult r = new CommonResult();
        r.msg = msg;
        r.code = errCode;
        return r;
    }

    public CommonResult<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

}
