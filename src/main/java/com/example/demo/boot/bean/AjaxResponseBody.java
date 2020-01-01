package com.example.demo.boot.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
 * 前后端项目，返回给前端需要保存的信息，当前用户信息，token
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class AjaxResponseBody implements Serializable{

    private String status;
    private String msg;
    private Object result;
    private String jwtToken;

}
