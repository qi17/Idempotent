package com.root.idempotent.service.impl;

/**
 * @author qch
 * @date 2021年11月09日 11:22 上午
 */

import com.root.idempotent.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    @Override
    public String test() {
        return "校验幂等性成功";
    }
}
