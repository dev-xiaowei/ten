package entity;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.Serializable;

/**
 * 响应实体
 */
public class Result implements Serializable{

    private Integer code;//响应吗
    private Boolean flag;//是否成功
    private String message;//响应消息
    private Object data;  // 响应业务内容

    public Result() {
    }

    public Result(Boolean flag , Integer code, String message) {
        this.code = code;
        this.flag = flag;
        this.message = message;
    }

    public Result(Boolean flag, Integer code, String message, Object data) {
        this.code = code;
        this.flag = flag;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
