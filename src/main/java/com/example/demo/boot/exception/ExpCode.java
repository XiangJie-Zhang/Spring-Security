package com.example.demo.boot.exception;

/**
 * 已知异常代码枚举
 *
 * @author: 04637@163.com
 * @date: 2019/5/25
 */
public enum ExpCode {

    // 未指定
    DEFAULT(500),

    // token 无效// 需要登陆
    TOKEN_INVALID(401),

    // 签名失败
    SIGN_TOKEN_FAILED(500),

    // 权限不足
    PERMISSION_DENIED(403),

    // 请求未经过表单验证
    UNPROCESSABLE_ENTITY(400),

    // 未找到该资源
    NOT_FOUND(404);

    private int val;

    public int getVal() {
        return val;
    }

    ExpCode(int val) {
        this.val = val;
    }

}
