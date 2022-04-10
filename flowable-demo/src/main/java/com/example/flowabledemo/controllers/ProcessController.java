package com.example.flowabledemo.controllers;

import com.example.flowabledemo.dtos.ProcessDTO;
import com.example.flowabledemo.dtos.TaskDTO;
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
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processKey);
        ProcessDTO processDTO = new ProcessDTO(processInstance.getProcessInstanceId());
        return processDTO;
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
            @PathVariable String taskId,
            @RequestParam(required = false) String jokeOrFact) {

        if (jokeOrFact != null) {
            Map<String, Object> variables = new HashMap<>();
            variables.put("jokeOrFact", jokeOrFact);
            taskService.complete(taskId, variables);
        } else {
            Map<String, Object> processVariableMap = new HashMap<>();
            List<String> testList = new ArrayList<>();
            testList.add("hallo");
            testList.add("velo");

            processVariableMap.put("testList", testList);
            processVariableMap.put("highNumber",5);
            processVariableMap.put("lowNumber", 3);
            taskService.complete(taskId, processVariableMap);
        }

        return getCurrentTaskListByProcessInstanceId(processInstanceId);
    }
}
