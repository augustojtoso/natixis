package com.natixis.taches.controller;

import com.natixis.taches.model.Task;
import com.natixis.taches.model.TaskStatusDTO;
import com.natixis.taches.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/tache/{id}")
public class TaskIdController {
    private TaskService tasksService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Task getTache(@PathVariable("id") long id) {
        //Récupérer une tâche par son ID
        return tasksService.getTask(id);
    }

    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    //Changer le statut d'une tâche
    public Task updateTaskStatus(
            @PathVariable("id") long id, @Valid @RequestBody TaskStatusDTO newStatus) {
        return tasksService.updateTaskStatus(id,newStatus);
    }
}
