package com.example.flowabledemo.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TaskDTO {
    public String taskDefinitionKey;
    public String taskName;
    public String taskId;
}
