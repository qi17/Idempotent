package com.root.idempotent.config;

import com.root.idempotent.inteceptor.IdempotentInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @author qch
 * @date 2021年11月09日 10:51 上午
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private IdempotentInterceptor idempotentInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(idempotentInterceptor);
    }
}
