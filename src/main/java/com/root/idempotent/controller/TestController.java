package com.root.idempotent.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.root.idempotent.annotation.AutoIdempotent;
import com.root.idempotent.common.Result;
import com.root.idempotent.enums.CommonEnum;
import com.root.idempotent.service.TestService;
import com.root.idempotent.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qch
 * @date 2021年11月09日 11:16 上午
 */
@RestController
@Slf4j
public class TestController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private TestService testService;

    @PostMapping("/get/token")
    public String getToken() {
        String token = tokenService.createToken();
        if (StrUtil.isNotEmpty(token)) {
            Result instance = Result.instance(CommonEnum.SUCCESS);
            return JSONUtil.toJsonStr(instance);
        }
        return StrUtil.EMPTY;
    }

    @AutoIdempotent
    @PostMapping("/test/Idempotence")
    public String testIdempotence() {
        String businessResult = testService.test();
        if (StrUtil.isNotEmpty(businessResult)) {
            return JSONUtil.toJsonStr(businessResult);
        }
        return StrUtil.EMPTY;
    }
}
