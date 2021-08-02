package com.natixis.taches.service;

import com.natixis.taches.model.Task;
import com.natixis.taches.model.TaskStatusDTO;
import com.natixis.taches.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepository tasksRepository;

    public List<Task> getAll() {
        return tasksRepository.findAll();
    }

    public List<Task> getUndoneTasks() {
        return tasksRepository.findByComplete(false);
    }

    public Task addNewTask(Task task) {
        return tasksRepository.save(task);
    }

    public Task getTask(long id) {
        return tasksRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tâche introuvable"));
    }

    public Task updateTaskStatus(long id, TaskStatusDTO newStatus) {
        var task = tasksRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tâche introuvable"));
        task.setComplete(newStatus.isNewStatus());
        return tasksRepository.save(task); //Update if exist
    }
}
