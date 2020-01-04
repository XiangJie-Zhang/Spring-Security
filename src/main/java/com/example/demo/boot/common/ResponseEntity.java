package com.example.demo.boot.common;


import com.example.demo.boot.exception.ExpCode;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

/**
 * 统一接口响应体
 *
 * @author: 04637
 * @date: 2019/4/29
 **/
@Data
@Accessors(chain = true)
public class ResponseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 标志请求是否成功
     */
    private boolean succeed;

    /**
     * 如有需要，存放错误信息
     */
    @Nullable
    private String msg;

    /**
     * 如有需要，存放响应数据结果，类型为json字符串, 如发生异常, 存放堆栈信息
     */
    @Nullable
    private Object data;

    /**
     * 异常代码
     */
    @Nullable
    private ExpCode expCode;

    /**
     * 请求被接受, 但是参数有问题没有处理成功
     *
     * @param msg 响应消息
     */
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public static ResponseEntity unProcess(String msg) {
        return new ResponseEntity().setMsg(msg).setSucceed(false);
    }

    /**
     * 操作成功返回消息
     *
     * @param msg 信息
     */
    @ResponseStatus(HttpStatus.OK)
    public static ResponseEntity ok(String msg) {
        return new ResponseEntity().setMsg(msg).setSucceed(true);
    }

    /**
     * 操作成功
     */
    @ResponseStatus(HttpStatus.OK)
    public static ResponseEntity ok() {
        return new ResponseEntity().setSucceed(true);
    }

    /**
     * 操作成功返回数据
     *
     * @param data 响应数据
     * @deprecated 谨慎使用此方法设置data, 若data类型为字符串则会设为msg 👆 建议使用 {@link ResponseEntity#setData(Object)}
     */
    @Deprecated
    @ResponseStatus(HttpStatus.OK)
    public static ResponseEntity ok(Object data) {
        return new ResponseEntity().setData(data).setSucceed(true);
    }

    /**
     * 请求成功完成
     * 但是结果失败 例如登录时用户名或密码错误
     *
     * @param msg 响应消息
     */
    @ResponseStatus(HttpStatus.OK)
    public static ResponseEntity failed(String msg) {
        return new ResponseEntity().setMsg(msg).setSucceed(false);
    }

}
