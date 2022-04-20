package com.example.flowabledemo.delegates;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.delegate.BpmnError;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class ServiceTaskExpression {
    @Autowired
    TaskService taskService;

    @Autowired
    RuntimeService runtimeService;

    public void logMessage() {
        System.out.println("Service Task Expression Bean (Service Task 3)");
    }

    public void doSomethingEvenMoreAmazing() {
        System.out.println("Service Task Expression Bean (Service Task 3) - Message 1");
        System.out.println("Service Task Expression Bean (Service Task 3) - Message 2");
    }

    public void throwError() {
        System.out.println("Mocking Error");
        throw new BpmnError("99999", "Something went wrong");
    }

    public void timeout() {
        System.out.println("Process has timed out!");
    }

    public void sendErrorEmail() {
        System.out.println("Send Error Email");
    }

    public void logFromDummySubprocess() {
        System.out.println("Service Task (Dummy Subprocess)");
    }

    public void logProcessVariables(DelegateExecution execution) {
        System.out.println(execution.getVariables().toString());
    }

    public void tellJoke() {
        System.out.println("Want to know how you make any salad into a caesar salad? Stab it twenty-three times.");
    }

    public void tellFact() {
        System.out.println("The Philippines consists of 7,641 islands.");
    }

    public void logCardinalityFixedNumberOfExecutions(DelegateExecution execution) {
        System.out.println("Hello from Fixed Number of Executions Cardinality Service Task");
    }

    public void logCardinalityVariableNumberOfExecutions(DelegateExecution execution) {
        Map<String, Object> variables = execution.getVariables();
        System.out.println("Hello from Variable Number of Executions Cardinality Service Task");
        System.out.println("High number: " + variables.get("highNumber").toString());
        System.out.println("Low Number: " + variables.get("lowNumber").toString());
    }

    public void logCardinalityCollectionVariable(DelegateExecution execution) {
        Map<String, Object> variables = execution.getVariables();
        System.out.println("Hello from Collection Variable Cardinality Service Task");
        System.out.println("List item: " + variables.get("testItem"));
    }
}
