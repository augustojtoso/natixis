package com.natixis.taches.controller;


import com.natixis.taches.model.Task;
import com.natixis.taches.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/tache")
@AllArgsConstructor
public class TaskController {
    private TaskService tasksService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    //Récupérer les tâches à effectuer
    public List<Task> getTasks(@RequestParam(value = "s", defaultValue = "all") String status) {
        switch (status){
            case "aeffectuer":
                return tasksService.getUndoneTasks();
            default:
                return tasksService.getAll();
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    //Ajouter des tâches
    public Task addNewTask(@Valid @RequestBody Task task){
        return tasksService.addNewTask(task);
    }

}
