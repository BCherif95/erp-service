package com.tuwindi.erp.erpservice.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBody {
    private String status = null;
    private String message = null;
    private Object response = null;

    public ResponseBody(String state, String msg) {
        this.message = msg;
        this.status = state;
    }

    public static ResponseBody with(Object object, String msg) {
        return new ResponseBody("OK", msg, object);
    }

    public static ResponseBody success(String msg) {
        return new ResponseBody("OK", msg);
    }

    public static ResponseBody error(String msg) {
        return new ResponseBody("KO", msg);
    }
}
