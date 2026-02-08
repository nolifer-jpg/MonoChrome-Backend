package com.monochrome.service;

import com.monochrome.dto.CreateTaskRequest;
import com.monochrome.dto.TaskResponse;
import java.util.List;

public interface TaskService {
    TaskResponse createTask(CreateTaskRequest request);
    List<TaskResponse> getAllTasks();
    void completeTask(Long id);
    void deleteTask(Long id);
}
