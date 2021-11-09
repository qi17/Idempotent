package com.root.idempotent.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import com.root.idempotent.RedisUtil;
import com.root.idempotent.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author qch
 * @date 2021年11月09日 10:05 上午
 */
@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private RedisUtil redisUtil;

    private static final String REDIS_TOKEN_CONSTANT_PREFIX = "id_empotent";
    private static final String REQUEST_TOKEN_NAME_CONSTANT = "x_redis_token";

    @Override
    public String createToken() {
        String str = RandomUtil.randomString("Root", 10);
        StringBuilder token = new StringBuilder();
        try {
            // 生成token字符串
            token.append(REDIS_TOKEN_CONSTANT_PREFIX).append(str);
            redisUtil.setExpire(token.toString(), token.toString(), 1000L);
            boolean notBlank = StrUtil.isNotBlank(token.toString());
            if (notBlank) {
                return token.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean checkToken(HttpServletRequest request) throws Exception {
        String token = request.getHeader(REQUEST_TOKEN_NAME_CONSTANT);
        // 从请求中获取token
        if (StrUtil.isBlank(token)) {
            token = request.getParameter(REQUEST_TOKEN_NAME_CONSTANT);
            if (StrUtil.isBlank(token)) {
                throw new Exception("token为空");
            }
        }
        if(!redisUtil.exists(token)){
            throw new Exception("缓存中token不存在");
        }
        boolean removeStatus = redisUtil.remove(token);
        if(!removeStatus){
            throw new Exception("移除缓存token失败");
        }
        return true;
    }
}
