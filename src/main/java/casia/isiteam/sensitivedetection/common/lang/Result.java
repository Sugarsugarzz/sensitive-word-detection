package casia.isiteam.sensitivedetection.common.lang;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {

    private int code;  // 200是正常，非200表示异常
    private String msg;
    private Object data;

    public static Result success(Object data) {
        return success(200, "操作成功!", data);
    }

    public static Result success(int code, String msg, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static Result fail() {
        return fail(400, "操作失败!", null);
    }

    public static Result fail(int code, String msg, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }
}
