package com.example.demo.boot.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 数据层异常
 *
 * @author: 04637
 * @date: 2019/4/30
 **/
@Getter
@Setter
@Accessors(chain = true)
public class MapperException extends BaseException {

    public MapperException(String msg) {
        super(msg);
    }
}
