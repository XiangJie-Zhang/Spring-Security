package com.example.demo.boot.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 基础自定义异常
 *
 * @author: 04637@163.com
 * @date: 2019/5/25
 */
@Getter
@Setter
@Accessors(chain = true)
class BaseException extends RuntimeException {

    /**
     * 异常相关对象
     */
    private Object object;

    /**
     * 源异常
     */
    private Throwable throwable;

    BaseException(String msg) {
        super(msg);
    }

}
