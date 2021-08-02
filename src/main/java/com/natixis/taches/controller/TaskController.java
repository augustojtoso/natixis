package com.natixis.taches.controller;


import com.natixis.taches.model.Task;
import com.natixis.taches.model.TaskDTO;
import com.natixis.taches.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/tache")
@Api(tags = "Tache", value = "Tache")
@AllArgsConstructor
public class TaskController {
    private TaskService tasksService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiOperation(value = "Récupérer tout les tâches ou les tâches a effectuer", tags = "Tache")
    public List<Task> getTasks(
            @ApiParam(value = "Filtrer les tâches pour le statut complete."
                    ,allowableValues = "aeffectuer,tout"
                    ,allowEmptyValue = true
                    ,required = false)
            @RequestParam(value = "filtre", required = false) Optional<String> status) {
        switch (status.orElse("")){
            case "aeffectuer":
                return tasksService.getUndoneTasks();
            default:
                return tasksService.getAll();
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiOperation(value = "Ajouter des tâches", tags = "Tache")
    public Task addNewTask(@Valid @RequestBody TaskDTO taskDTO){
        return tasksService.addNewTask(taskDTO);
    }

}
