package ru.kmetha.gbapimay.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "gb.api")
public class GbApiProperties {

    private Endpoint endpoint;
    private Connection connection;

    @Getter
    @Setter
    public static class Endpoint {
        private String manufacturerUrl;
        private String productUrl;
        private String categoryUrl;
    }

    @Getter
    @Setter
    public static class Connection {
        private long period;
        private long maxPeriod;
        private int maxAttempts;
        long connectTimeout;
//        TimeUnit connectTimeoutUnit;
        long readTimeout;
//        TimeUnit readTimeoutUnit;
        boolean followRedirect;
    }
}
