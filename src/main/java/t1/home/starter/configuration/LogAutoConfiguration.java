package t1.home.starter.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import t1.home.starter.interceptor.LogInterceptor;

@AutoConfiguration
@EnableConfigurationProperties(LogProperties.class)
@ConditionalOnProperty(prefix = "logging", value = "enabled", havingValue = "true")
public class LogAutoConfiguration implements WebMvcConfigurer {
    @Autowired
    private LogProperties logProperties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor(logProperties.getLogLevel(), logProperties.getRequestMethod(),
                logProperties.getRequestUrl(), logProperties.getRequestHeaders(), logProperties.getRequestParam(),
                logProperties.getResponseStatus(), logProperties.getResponseHeaders(),
                logProperties.getResponseProcessingTime()));
    }

}
