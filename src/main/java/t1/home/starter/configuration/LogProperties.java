package t1.home.starter.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "logging")
public class LogProperties {
    private Boolean request;
    private String logLevel;
    private Boolean requestMethod;
    private Boolean requestUrl;
    private Boolean requestHeaders;
    private Boolean requestParam;
    private Boolean responseStatus;
    private Boolean responseHeaders;
    private Boolean responseProcessingTime;
}
