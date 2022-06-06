package com.example.flowabledemo.controllers;

import com.example.flowabledemo.dtos.ProcessDTO;
import com.example.flowabledemo.dtos.TaskDTO;
import com.example.flowabledemo.models.OnboardingEligibilityCheckProcessModel;
import com.example.flowabledemo.models.Partner;
import com.example.flowabledemo.models.SimplePartnershipDomainModel;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/process")
public class ProcessController {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;


    @PostMapping("/{processKey}")
    public ProcessDTO startProcessByKey(@PathVariable String processKey) {
        switch (processKey) {
            case "parallelUserTaskProcess":
            case "userTaskServiceTaskProcess":
            case "errorServiceTaskProcess":
            case "callActivityProcess":
            case "uelExperimentalProcess": {
                System.out.println("Starting a " + processKey + " process");
                ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processKey);
                ProcessDTO processDTO = new ProcessDTO(processInstance.getProcessInstanceId());
                return processDTO;
            }

            case "exclusiveUserTaskProcess": {
                System.out.println("Starting a " + processKey + " process");
                Map<String, Object> processVariables = new HashMap<>();
                //processVariables.put("jokeOrFact", "joke");
                processVariables.put("jokeOrFact", "anythingElseThatTriggersTheDefaultFlow");

                ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processKey, processVariables);
                ProcessDTO processDTO = new ProcessDTO(processInstance.getProcessInstanceId());
                return processDTO;
            }

            case "cardinalityProcess": {
                System.out.println("Starting a " + processKey + " process");

                Map<String, Object> processVariables = new HashMap<>();

                // Example: Cardinality with list elements
                List<String> fruitList = new ArrayList<>();
                fruitList.add("apple");
                fruitList.add("banana");
                processVariables.put("testList", fruitList);

                // Example: Cardinality with set boundaries
                processVariables.put("highNumber", 5);
                processVariables.put("lowNumber", 3);

                ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processKey, processVariables);
                ProcessDTO processDTO = new ProcessDTO(processInstance.getProcessInstanceId());
                return processDTO;
            }
            case "mainProcess": {
                System.out.println("Starting a " + processKey + " process");

                Map<String, Object> processVariables = new HashMap<>();
                processVariables.put("mainProcessVariable", "Guten Abend");

                ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processKey, processVariables);
                ProcessDTO processDTO = new ProcessDTO(processInstance.getProcessInstanceId());
                return processDTO;
            }

            case "inheritedVariablesProcess":
            case "inputOutputVariablesProcess": {
                System.out.println("Starting a " + processKey + " process");

                Partner partner1 = new Partner("Nicolas", "Kilchenmann", 29, false, true);
                Partner partner2 = new Partner("Christine", "Albornoz", 26, true, false);

                List<Partner> partnerList = new ArrayList<>();
                partnerList.add(partner1);
                partnerList.add(partner2);

                SimplePartnershipDomainModel simplePartnershipDomainModel = new SimplePartnershipDomainModel(partnerList);

                Map<String, Object> processVariables = new HashMap<>();
                processVariables.put("simplePartnershipDomainModel", simplePartnershipDomainModel);
                processVariables.put("partnerList", partnerList);

                OnboardingEligibilityCheckProcessModel onboardingEligibilityCheckProcessModel =
                        new OnboardingEligibilityCheckProcessModel(partnerList);
                processVariables.put("onboardingEligibilityCheckProcessModel", onboardingEligibilityCheckProcessModel);

                ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processKey, processVariables);
                ProcessDTO processDTO = new ProcessDTO(processInstance.getProcessInstanceId());
                return processDTO;
            }

            default: {
                System.out.println("Not starting any process");
                return null;
            }
        }
    }

    @GetMapping("/{processInstanceId}")
    public List<TaskDTO> getCurrentTaskListByProcessInstanceId(@PathVariable String processInstanceId) {
        List<Task> taskList = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
        List<TaskDTO> taskDTOList = taskList
                .stream().map(task -> {
                    TaskDTO taskDTO = new TaskDTO();
                    taskDTO.setTaskName(task.getName());
                    taskDTO.setTaskDefinitionKey(task.getTaskDefinitionKey());
                    taskDTO.setTaskId(task.getId());
                    return taskDTO;
                }).collect(Collectors.toList());
        return taskDTOList;
    }

    @PostMapping("/{processInstanceId}/{taskId}/complete")
    public List<TaskDTO> completeTaskById(
            @PathVariable String processInstanceId,
            @PathVariable String taskId) {

        taskService.complete(taskId);
        return getCurrentTaskListByProcessInstanceId(processInstanceId);
    }
}
