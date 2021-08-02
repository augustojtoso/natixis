package com.natixis.taches.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class TaskDTO {

    @ApiModelProperty(name = "label", required = true, dataType = "String", value = "la description de la tâche", example = "tâche a fare")
    @NotBlank
    private String label;

    @ApiModelProperty(name = "complete", dataType = "Boolean", value = "indique si la tâche est effectuée ou non", example = "false")
    private Boolean complete;
}
