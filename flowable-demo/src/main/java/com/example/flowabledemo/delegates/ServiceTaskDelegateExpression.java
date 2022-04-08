package com.example.flowabledemo.delegates;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class ServiceTaskDelegateExpression implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println("Hello from Service Task Delegate Expression Bean (Service Task 2)");
    }
}
