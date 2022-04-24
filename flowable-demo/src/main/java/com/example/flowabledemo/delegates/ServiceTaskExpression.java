package com.example.flowabledemo.delegates;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.delegate.BpmnError;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.impl.persistence.entity.ExecutionEntityImpl;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
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
        String executionEntity = ((ExecutionEntityImpl) execution).getProcessDefinitionKey();
        System.out.println(executionEntity + ": " + execution.getVariables().toString());
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

        if (variables.get("updatedTestList") == null) {
            List<String> updatedFruitList = new ArrayList<>();
            updatedFruitList.add(variables.get("testItem").toString() + " updated");
            execution.setVariable("updatedTestList", updatedFruitList);

        } else {
            List<String> updatedFruitList = (List<String>) variables.get("updatedTestList");
            updatedFruitList.add(variables.get("testItem").toString() + " updated");
            execution.setVariable("updatedTestList", updatedFruitList);

        }
    }

    public void writeProcessVariablePassedVariablesSubprocess(DelegateExecution execution) {
        String newString = "Magandang Gabi";
        String executionEntity = ((ExecutionEntityImpl) execution).getProcessDefinitionKey();
        System.out.println(executionEntity + ": Setting process variable 'subprocessVariable' to: " + newString);
        execution.setVariable("subprocessVariable", newString);
    }

    public void writeProcessVariableInheritedVariablesSubprocess(DelegateExecution execution) {
        String newString = "Good Evening";
        String executionEntity = ((ExecutionEntityImpl) execution).getProcessDefinitionKey();
        System.out.println(executionEntity + ": Setting process variable 'mainProcessVariable' to: " + newString);
        execution.setVariable("mainProcessVariable", newString);
    }
}
