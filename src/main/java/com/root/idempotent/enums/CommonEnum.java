package com.root.idempotent.enums;

/**
 * @author qch
 * @date 2021年11月09日 1:45 下午
 * 通用枚举类
 */
public enum CommonEnum {

    /** 系统操作成功 */
    SUCCESS("000000", "操作成功"),
    /** 系统中运行中出现异常 */
    ERROR("000001", "系统异常"),;
    private String code;
    private String msg;

    CommonEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }

}
