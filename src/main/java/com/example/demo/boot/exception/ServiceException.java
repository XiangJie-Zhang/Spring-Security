package com.example.demo.boot.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.lang.NonNull;

/**
 * 业务层异常
 *
 * @author: 04637
 * @date: 2019/4/30
 **/
@Getter
@Setter
@Accessors(chain = true)
public class ServiceException extends BaseException {

    /**
     * 异常代码
     */
    @NonNull
    private ExpCode expCode = ExpCode.DEFAULT;

    public ServiceException(String msg) {
        super(msg);
    }
}
