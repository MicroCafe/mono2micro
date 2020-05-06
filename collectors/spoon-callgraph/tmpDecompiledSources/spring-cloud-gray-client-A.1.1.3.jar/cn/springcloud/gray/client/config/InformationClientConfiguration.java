/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
 *  org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
 *  org.springframework.cloud.client.loadbalancer.LoadBalanced
 *  org.springframework.context.annotation.Bean
 *  org.springframework.context.annotation.Configuration
 *  org.springframework.web.client.RestTemplate
 */
package cn.springcloud.gray.client.config;

import cn.springcloud.gray.client.config.properties.GrayServerProperties;
import cn.springcloud.gray.communication.HttpInformationClient;
import cn.springcloud.gray.communication.InformationClient;
import cn.springcloud.gray.communication.RetryableInformationClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ConditionalOnProperty(value={"gray.server.url"})
public class InformationClientConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public InformationClient informationClient(@Autowired(required=false) RestTemplate grayInformationRestTemplate, GrayServerProperties grayServerProperties) {
        HttpInformationClient httpClient = new HttpInformationClient(grayServerProperties.getUrl(), grayInformationRestTemplate);
        if (grayServerProperties.isRetryable()) {
            return new RetryableInformationClient(Math.max(3, grayServerProperties.getRetryNumberOfRetries()), httpClient);
        }
        return httpClient;
    }

    @Configuration
    @ConditionalOnProperty(value={"gray.server.loadbalanced"})
    public static class LoadBalancedGrayInformationRestTemplate {
        @Bean(value={"grayInformationRestTemplate"})
        @LoadBalanced
        @ConditionalOnMissingBean(name={"grayInformationRestTemplate"})
        public RestTemplate grayInformationRestTemplate() {
            return new RestTemplate();
        }
    }

    @Configuration
    @ConditionalOnProperty(value={"gray.server.loadbalanced"}, havingValue="false", matchIfMissing=true)
    public static class DefaultGrayInformationRestTemplate {
        @Bean(value={"grayInformationRestTemplate"})
        @ConditionalOnMissingBean(name={"grayInformationRestTemplate"})
        public RestTemplate grayInformationRestTemplate() {
            return new RestTemplate();
        }
    }

}

