package com.zpc.configuration;

import com.alibaba.fastjson.serializer.SerializerFeature;

import com.zpc.converter.FastJsonpHttpMessageConverter;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

/**
 * HttpMessageConverters
 */
@Configuration
public class FastJsonHttpConfiguration {

    /**
     * @return
     */
    @Bean
    public HttpMessageConverters customConverters() {
        FastJsonpHttpMessageConverter fastConverter = new FastJsonpHttpMessageConverter();
        fastConverter.setFeatures(SerializerFeature.WriteMapNullValue,
            SerializerFeature.DisableCircularReferenceDetect);
        HttpMessageConverter<?> converter = fastConverter;
        return new HttpMessageConverters(converter);
    }
}
