package me.jinkun.rds.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Fastjso配置
 */
@Configuration
public class FastJsonConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(FastJsonConfiguration.class);

    @Autowired
    private FastJsonConfig fastJsonConfig;

    @Bean
    public FastJsonConfig fastJsonConfig() {
        logger.info("初始化 FastJsonConfig 配置");
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //    SerializerFeature.WriteMapNullValue,
        //    SerializerFeature.WriteNullListAsEmpty,
        //    SerializerFeature.WriteNullNumberAsZero,
        //    SerializerFeature.WriteNullBooleanAsFalse,
        //    SerializerFeature.WriteDateUseDateFormat,
        //    SerializerFeature.WriteNullStringAsEmpty
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        return fastJsonConfig;
    }

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        logger.info("初始化 FastJson Converter 配置");
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        fastConverter.setFastJsonConfig(fastJsonConfig);
        return new HttpMessageConverters(fastConverter);
    }
}
