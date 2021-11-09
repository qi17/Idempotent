package com.root.idempotent.service;


import javax.servlet.http.HttpServletRequest;

/**
 * @author qch
 * @date 2021年11月09日 10:05 上午
 */


public interface TokenService {


    /**
     * 创建token
     */
    public String createToken();

    /**
     * 校验token
     */
    public boolean checkToken(HttpServletRequest request) throws Exception;

}
