package com.fenbi.newbull.mvc.web.configure;

import com.fenbi.newbull.mvc.web.data.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 配置WebMvc
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    public static class StringToUserConverter implements Converter<String, User> {

        @Override
        public User convert(String from) {
            int id = Integer.valueOf(from);
            return id >= 0 ? new User(id) : null;
        }
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToUserConverter());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor());
    }
}
