package me.jinkun.rds.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Fastjso配置
 */
@Configuration
public class FastJsonConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(FastJsonConfiguration.class);


//    SerializerFeature.WriteMapNullValue,
//    SerializerFeature.WriteNullListAsEmpty,
//    SerializerFeature.WriteNullNumberAsZero,
//    SerializerFeature.WriteNullBooleanAsFalse,
//    SerializerFeature.WriteDateUseDateFormat,
//    SerializerFeature.WriteNullStringAsEmpty
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        logger.info("初始化 FastJson Converter 配置");
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        fastConverter.setFastJsonConfig(fastJsonConfig);
        return new HttpMessageConverters(fastConverter);
    }
}
