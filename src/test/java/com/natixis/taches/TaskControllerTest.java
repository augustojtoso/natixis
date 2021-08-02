package com.natixis.taches;

import com.natixis.taches.controller.TaskController;
import com.natixis.taches.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {
    @Mock
    TaskService service;

    @InjectMocks
    TaskController controller;

    @Test
    public void test_getAll_noParam(){
        //given
        Optional<String> defaultParam = Optional.empty();
        //when
        controller.getTasks(defaultParam);
        //then
        verify(service, times(1)).getAll();
    }

    @Test
    public void test_getAll_badParam(){
        //given
        var defaultParam =  Optional.of("bla");
        //when
        controller.getTasks(defaultParam);
        //then
        verify(service, times(1)).getAll();
    }

    @Test
    public void test_getUndone(){
        //given
        var defaultParam =  Optional.of("aeffectuer");
        //when
        controller.getTasks(defaultParam);
        //then
        verify(service, times(1)).getUndoneTasks();
    }
}
