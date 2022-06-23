package com.example.flowabledemo.delegates;

import com.example.flowabledemo.ErrorCodes;
import com.example.flowabledemo.models.OnboardingEligibilityCheckProcessModel;
import com.example.flowabledemo.models.Partner;
import com.example.flowabledemo.models.SimplePartnershipDomainModel;
import com.example.flowabledemo.util.UtilServices;
import liquibase.pro.packaged.R;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.delegate.BpmnError;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.impl.persistence.entity.ExecutionEntity;
import org.flowable.engine.impl.persistence.entity.ExecutionEntityImpl;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

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

    public void throwBPMNError(String errorCode) {
        System.out.println("Throwing BPMN error: " + errorCode);
        //TODO: improvement to error codes (make dynamic)
        throw new BpmnError(errorCode, ErrorCodes.ERROR_88888.getErrorMessage());
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

    public void logProcessVariables(ExecutionEntityImpl execution) {
        System.out.println(execution.getProcessDefinitionKey() + ": " + execution.getVariables().toString());
    }

    public void tellJoke() {
        System.out.println("Want to know how you make any salad into a caesar salad? Stab it twenty-three times.");
    }

    public void tellFact() {
        System.out.println("The Philippines consists of 7,641 islands.");
    }

    public void logCardinalityFixedNumberOfExecutions(ExecutionEntityImpl execution) {
        System.out.println("Hello from Fixed Number of Executions Cardinality Service Task");
    }

    public void logCardinalityVariableNumberOfExecutions(ExecutionEntityImpl execution) {
        Map<String, Object> variables = execution.getVariables();
        System.out.println("Hello from Variable Number of Executions Cardinality Service Task");
        System.out.println("High number: " + variables.get("highNumber").toString());
        System.out.println("Low Number: " + variables.get("lowNumber").toString());
    }

    public void logCardinalityCollectionVariable(ExecutionEntityImpl execution) {
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

    public void writeProcessVariablePassedVariablesSubprocess(ExecutionEntityImpl execution) {
        String newString = "Magandang Gabi";
        System.out.println(execution.getProcessDefinitionKey() + ": Setting process variable 'subprocessVariable' to: " + newString);
        execution.setVariable("subprocessVariable", newString);
    }

    public void writeProcessVariableInheritedVariablesSubprocess(ExecutionEntityImpl execution) {
        String newString = "Good Evening";
        System.out.println(execution.getProcessDefinitionKey() + ": Setting process variable 'mainProcessVariable' to: " + newString);
        execution.setVariable("mainProcessVariable", newString);
    }

    public void evaluateString(ExecutionEntityImpl execution, String testString) {
        System.out.println(testString);
    }

    public OnboardingEligibilityCheckProcessModel mapToSubprocessModel(ExecutionEntityImpl execution) {

        // partner list
        List<Partner> partnerList = ((SimplePartnershipDomainModel) execution.getVariable("simplePartnershipDomainModel")).getLinkedPartners();

        OnboardingEligibilityCheckProcessModel onboardingEligibilityCheckProcessModel =
                new OnboardingEligibilityCheckProcessModel(partnerList);

        return onboardingEligibilityCheckProcessModel;
    }

    public void mapDomainModelToSubprocessModel(ExecutionEntityImpl execution) {
        List<Partner> partnerList = ((SimplePartnershipDomainModel) execution.getVariable("simplePartnershipDomainModel")).getLinkedPartners();
        OnboardingEligibilityCheckProcessModel onboardingEligibilityCheckProcessModel =
                new OnboardingEligibilityCheckProcessModel(partnerList);
        execution.setVariable("onboardingEligibilityCheckProcessModel", onboardingEligibilityCheckProcessModel);
    }

    public void executeDropoutCheckInheritedVariables(ExecutionEntityImpl execution) {
        Partner partner = ((Partner) execution.getVariable("partner"));
        if (partner.getExecuteDropoutCheck()) {
            System.out.println("Executing Dropout Check for: " + partner.getFirstName() + " " + partner.getLastName());
            partner.setDropoutCheckStatus(UtilServices.getDropoutOrCosimaResult());
        } else {
            System.out.println("NOT Executing Dropout Check for: " + partner.getFirstName() + " " + partner.getLastName());
        }
    }

    public void executeCosimaCheckInheritedVariables(ExecutionEntityImpl execution) {
        Partner partner = ((Partner) execution.getVariable("partner"));
        if (partner.getExecuteCosimaCheck()) {
            System.out.println("Executing Cosima Check for: " + partner.getFirstName() + " " + partner.getLastName());
            partner.setCosimaCheckStatus(UtilServices.getDropoutOrCosimaResult());
        } else {
            System.out.println("NOT Executing Cosima Check for: " + partner.getFirstName() + " " + partner.getLastName());
        }
    }

    public List<Partner> getMultiInstanceCollectionVariableInheritedVariables(ExecutionEntityImpl execution) {
        List<Partner> partnerList = ((SimplePartnershipDomainModel) execution.getVariable("simplePartnershipDomainModel")).getLinkedPartners();
        return partnerList;
    }

    public void logDropoutCosimaProcessVariables(ExecutionEntityImpl execution) {
        System.out.println(((SimplePartnershipDomainModel) execution.getVariable("simplePartnershipDomainModel")));
    }

    public void logError() {
        System.out.println("AN ERROR HAS BEEN DETECTED - TERMINATING THE PROCESS");
    }

}
