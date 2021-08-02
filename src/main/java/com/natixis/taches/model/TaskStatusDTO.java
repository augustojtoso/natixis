package com.natixis.taches.model;

import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class TaskStatusDTO {
    @NotNull
    private boolean newStatus;
}
