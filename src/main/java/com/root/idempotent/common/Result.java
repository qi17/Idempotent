package com.root.idempotent.common;

import com.root.idempotent.enums.CommonEnum;

import java.io.Serializable;

/**
 * @author qch
 * @date 2021年11月09日 10:46 上午
 * 自定义返回通用结果类
 */
public class Result<T> implements Serializable {
    private static final Long serialVersionUID = 173403404940267848L;
    private String code;
    private String desc;
    private T data;

    public Result() {
    }

    public Result(String code, String desc, T data) {
        this.code = code;
        this.desc = desc;
        this.data = data;
    }

    public static <T> Result<T> SUCCESS(CommonEnum commonEnum) {
        return instance(commonEnum.getCode(), commonEnum.getMsg());
    }

    public static <T> Result<T> SUCCESS(CommonEnum commonEnum, T t) {
        return instance(commonEnum.getCode(), commonEnum.getMsg(), t);
    }

    public static <T> Result<T> instance(CommonEnum commonEnum, T t) {
        return instance(commonEnum.getCode(), commonEnum.getMsg(), t);
    }

    public static Result instance(CommonEnum commonEnum) {
        return instance(commonEnum.getCode(), commonEnum.getMsg());
    }

    public static Result instance(String code, String desc) {
        return new Result(code, desc, null);
    }

    public static <T> Result<T> instance(String code, String desc, T t) {
        return new Result(code, desc, t);
    }
}
