package me.jinkun.rds.config;

import me.jinkun.rds.core.support.mybatis.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis拦截器配置
 */
@Configuration
public class MyBatisConfigration {

    private static final Logger log = LoggerFactory.getLogger(MyBatisConfigration.class);

    /**
     * MyBatis分页和排序拦截器
     */
    @Bean
    public Interceptor getInterceptor() {
        log.info("初始化MyBatis的分页插件");
        return new PageInterceptor();
    }
}
