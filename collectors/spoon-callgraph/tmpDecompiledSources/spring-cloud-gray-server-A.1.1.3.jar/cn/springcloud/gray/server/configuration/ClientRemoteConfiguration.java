/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
 *  org.springframework.context.annotation.Bean
 *  org.springframework.context.annotation.Configuration
 */
package cn.springcloud.gray.server.configuration;

import cn.springcloud.gray.server.discovery.ServiceDiscovery;
import cn.springcloud.gray.server.module.client.ClientRemoteModule;
import cn.springcloud.gray.server.module.client.DefaultClientRemoteModule;
import cn.springcloud.gray.server.module.gray.GrayServerModule;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientRemoteConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public ClientRemoteModule clientRemoteModule(ServiceDiscovery serviceDiscovery, GrayServerModule grayServerModule) {
        return new DefaultClientRemoteModule(serviceDiscovery, grayServerModule);
    }
}

