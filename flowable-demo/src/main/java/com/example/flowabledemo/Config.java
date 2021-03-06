package com.example.flowabledemo;

import com.example.flowabledemo.delegates.ServiceTaskExpression;
import com.example.flowabledemo.delegates.ServiceTaskDelegateExpression;
import com.example.flowabledemo.delegates.SimplePartnershipServiceTask;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean(name = "serviceTaskDelegateExpression")
    public ServiceTaskDelegateExpression getServiceTaskDelegateExpression() {
        return new ServiceTaskDelegateExpression();
    }

    @Bean(name = "serviceTaskExpression")
    public ServiceTaskExpression getServiceTaskExpression() {
        return new ServiceTaskExpression();
    }

    @Bean(name = "simplePartnershipServiceTask")
    public SimplePartnershipServiceTask getSimplePartnershipServiceTask() {
        return new SimplePartnershipServiceTask();
    }
}
