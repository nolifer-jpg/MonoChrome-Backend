package com.monochrome.service;

import com.monochrome.domain.Task;
import com.monochrome.dto.CreateTaskRequest;
import com.monochrome.dto.TaskResponse;
import com.monochrome.exception.TaskNotFoundException;
import com.monochrome.repository.TaskRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskResponse createTask(CreateTaskRequest request) {
        Task task = new Task(request.description());
        Task saved = taskRepository.save(task);
        return new TaskResponse(
            saved.getId(),
            saved.getDescription(),
            saved.isCompleted(),
            saved.getCreatedAt()
        );
    }

    @Override
    public List<TaskResponse> getAllTasks() {
        return taskRepository
            .findAll()
            .stream()
            .map(task ->
                new TaskResponse(
                    task.getId(),
                    task.getDescription(),
                    task.isCompleted(),
                    task.getCreatedAt()
                )
            )
            .toList();
    }

    @Override
    public void completeTask(Long id) {
        Task task = taskRepository
            .findById(id)
            .orElseThrow(() -> new TaskNotFoundException(id));
        task.markCompleted();
        taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        taskRepository.deleteById(id);
    }
}
