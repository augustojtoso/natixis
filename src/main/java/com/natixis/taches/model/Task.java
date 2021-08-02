package com.natixis.taches.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Builder
@Data
@AllArgsConstructor
public class Task {

    public Task(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String label;
    //Default value
    private boolean complete = false;
}