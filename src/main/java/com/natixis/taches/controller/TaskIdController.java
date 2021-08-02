package com.natixis.taches.controller;

import com.natixis.taches.model.Task;
import com.natixis.taches.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Api(tags = "Tache")
@RequestMapping(value = "/tache/{id}")
public class TaskIdController {
    private TaskService tasksService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiOperation(value = "Récupérer une tâche par son ID", tags = "Tache")
    public Task getTache(@PathVariable("id") long id) {
        return tasksService.getTask(id);
    }

    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiOperation(value = "Changer le statut d'une tâche", tags = "Tache")
    public Task updateTaskStatus(@PathVariable("id") long id,
                                 @ApiParam(name =  "Complete", value = "Indique le nouvelle value pour complete", required = true)
                                 @RequestBody Boolean complete) {
        return tasksService.updateTaskStatus(id, complete);
    }
}
