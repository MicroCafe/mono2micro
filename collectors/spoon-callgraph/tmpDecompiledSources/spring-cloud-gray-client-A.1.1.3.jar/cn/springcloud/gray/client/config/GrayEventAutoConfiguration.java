/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  cn.springcloud.gray.event.GrayEventListener
 *  org.springframework.boot.autoconfigure.condition.ConditionalOnBean
 *  org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
 *  org.springframework.context.annotation.Bean
 *  org.springframework.context.annotation.Configuration
 */
package cn.springcloud.gray.client.config;

import cn.springcloud.gray.GrayManager;
import cn.springcloud.gray.UpdateableGrayManager;
import cn.springcloud.gray.event.GrayEventListener;
import cn.springcloud.gray.event.GraySourceEventListener;
import cn.springcloud.gray.event.sourcehander.GrayDecisionEventHandler;
import cn.springcloud.gray.event.sourcehander.GrayInstanceEventHandler;
import cn.springcloud.gray.event.sourcehander.GrayPolicyEventHandler;
import cn.springcloud.gray.event.sourcehander.GrayServiceEventHandler;
import cn.springcloud.gray.event.sourcehander.GraySourceEventHandler;
import cn.springcloud.gray.event.sourcehander.GrayTrackEventHandler;
import cn.springcloud.gray.event.sourcehander.SourceHanderService;
import cn.springcloud.gray.local.InstanceLocalInfoInitiralizer;
import cn.springcloud.gray.request.track.CommunicableGrayTrackHolder;
import java.util.List;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnBean(value={GrayManager.class})
public class GrayEventAutoConfiguration {
    @Bean
    public GrayTrackEventHandler grayTrackEventHandler(InstanceLocalInfoInitiralizer instanceLocalInfoInitiralizer, CommunicableGrayTrackHolder grayTrackHolder) {
        return new GrayTrackEventHandler(instanceLocalInfoInitiralizer, grayTrackHolder);
    }

    @Bean
    public GrayInstanceEventHandler grayInstanceEventHandler(InstanceLocalInfoInitiralizer instanceLocalInfoInitiralizer, GrayManager grayManager) {
        return new GrayInstanceEventHandler(grayManager, instanceLocalInfoInitiralizer);
    }

    @Bean
    public GrayDecisionEventHandler grayDecisionEventHandler(InstanceLocalInfoInitiralizer instanceLocalInfoInitiralizer, UpdateableGrayManager grayManager) {
        return new GrayDecisionEventHandler(grayManager, instanceLocalInfoInitiralizer);
    }

    @Bean
    public GrayServiceEventHandler grayServiceEventHandler(InstanceLocalInfoInitiralizer instanceLocalInfoInitiralizer, UpdateableGrayManager grayManager) {
        return new GrayServiceEventHandler(grayManager, instanceLocalInfoInitiralizer);
    }

    @Bean
    public GrayPolicyEventHandler grayPolicyEventHandler(InstanceLocalInfoInitiralizer instanceLocalInfoInitiralizer, UpdateableGrayManager grayManager) {
        return new GrayPolicyEventHandler(grayManager, instanceLocalInfoInitiralizer);
    }

    @Bean
    @ConditionalOnMissingBean
    public SourceHanderService sourceHanderService(List<GraySourceEventHandler> handlers) {
        return new SourceHanderService.Default(handlers);
    }

    @Bean
    @ConditionalOnMissingBean
    public GrayEventListener grayEventListener(SourceHanderService sourceHanderService) {
        return new GraySourceEventListener(sourceHanderService);
    }
}

