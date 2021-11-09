package com.root.idempotent;

import cn.hutool.core.util.StrUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author jitwxs
 * @date 2021年11月09日 9:29 上午
 */
@Component
public class RedisUtil {
    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 写入缓存
     */
    public boolean set(String key, Object val) throws Exception {
        boolean resFlag = false;
        if (StrUtil.isBlank(key) || Objects.isNull(val)) {
            throw new Exception("key或value不得为空");
        }
        try {
            ValueOperations valOperation = redisTemplate.opsForValue();
            valOperation.set(key, val);
            resFlag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resFlag;
    }

    /**
     * 写入缓存设置过期时间
     */
    public boolean setExpire(String key, Object val, Long exp) {
        boolean resFlag = false;
        try {
            ValueOperations valueOperations = redisTemplate.opsForValue();
            valueOperations.set(key, val, exp, TimeUnit.SECONDS);
            resFlag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resFlag;
    }

    /**
     * 判断缓存是否存在
     */
    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     */
    public Object get(String key) {
        ValueOperations<Serializable, Object> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);

    }

    /**
     * 删除对应的value
     */
    public boolean remove(String key){
        if(exists(key)){
            return redisTemplate.delete(key);
        }
        return false;
    }

}
