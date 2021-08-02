package com.natixis.taches.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.natixis.taches.model.Task;
import com.natixis.taches.model.TaskDTO;
import com.natixis.taches.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository tasksRepository;
    private final ObjectMapper objectMapper;

    public List<Task> getAll() {
        return tasksRepository.findAll();
    }

    public List<Task> getUndoneTasks() {
        return tasksRepository.findByComplete(false);
    }

    public Task addNewTask(TaskDTO taskDto) {
        var task = objectMapper.convertValue(taskDto,Task.class);
        return tasksRepository.save(task);
    }

    public Task getTask(long id) {
        return tasksRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tâche introuvable"));
    }

    public Task updateTaskStatus(long id, Boolean complete) {
        var task = tasksRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tâche introuvable"));
        task.setComplete(complete);
        return tasksRepository.save(task); //Update if exist
    }
}
