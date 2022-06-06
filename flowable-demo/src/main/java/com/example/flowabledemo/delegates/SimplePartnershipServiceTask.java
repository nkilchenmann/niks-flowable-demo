package com.example.flowabledemo.delegates;

import com.example.flowabledemo.models.OnboardingEligibilityCheckProcessModel;
import com.example.flowabledemo.models.Partner;
import com.example.flowabledemo.util.UtilServices;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.impl.persistence.entity.ExecutionEntityImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimplePartnershipServiceTask {
    @Autowired
    TaskService taskService;

    @Autowired
    RuntimeService runtimeService;

    public void doDummyInterruption(ExecutionEntityImpl execution) {
        System.out.println("Process is interrupted");
    }

    public void executeDropoutCheckInputOutputVariables(ExecutionEntityImpl execution) {
        // load onboarding eligibility data model from variables
        //TODO: is that even needed? apparently the partner data modification updates the variable accordingly already
        /*OnboardingEligibilityCheckProcessModel onboardingEligibilityCheckProcessModel =
                ((OnboardingEligibilityCheckProcessModel) execution.getVariable("subprocessOnboardingEligibilityCheckProcessModel"));*/

        // load partner from loop variable
        Partner partner = ((Partner) execution.getVariable("subprocessPartner"));

        // execute dropout check
        //TODO: remove logic here into gateway
        if (partner.getExecuteDropoutCheck()) {
            System.out.println("Executing Dropout Check for: " + partner.getFirstName() + " " + partner.getLastName());
            partner.setDropoutCheckStatus(UtilServices.getDropoutOrCosimaResult());
        } else {
            System.out.println("NOT Executing Dropout Check for: " + partner.getFirstName() + " " + partner.getLastName());
        }
    }

    public void executeCosimaCheckInputOutputVariables(ExecutionEntityImpl execution) {
        // load onboarding eligibility data model from variables
        //TODO: is that even needed?
        /*OnboardingEligibilityCheckProcessModel onboardingEligibilityCheckProcessModel =
                ((OnboardingEligibilityCheckProcessModel) execution.getVariable("subprocessOnboardingEligibilityCheckProcessModel"));*/

        // load partner from loop variables
        Partner partner = ((Partner) execution.getVariable("subprocessPartner"));

        // execute cosima check
        if (partner.getExecuteCosimaCheck()) {
            System.out.println("Executing Cosima Check for: " + partner.getFirstName() + " " + partner.getLastName());
            partner.setCosimaCheckStatus(UtilServices.getDropoutOrCosimaResult());
        } else {
            System.out.println("NOT Executing Cosima Check for: " + partner.getFirstName() + " " + partner.getLastName());
        }
    }

    public void mapBack(ExecutionEntityImpl execution) {
        System.out.println("Process is interrupted");
    }
}
