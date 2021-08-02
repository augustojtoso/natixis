package com.natixis.taches;

import com.natixis.taches.model.Task;
import com.natixis.taches.model.TaskStatusDTO;
import com.natixis.taches.repository.TaskRepository;
import com.natixis.taches.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private static TaskRepository repository;

    @InjectMocks
    private static TaskService service;

    @Captor
    ArgumentCaptor<Task> captor;

    @Test
    public void test_getAllTasks(){
        //given
        var task = Task.builder().id(1L).label("test").complete(false).build();
        when(repository.findAll()).thenReturn(Collections.singletonList(task));
        //When
        List<Task> list = service.getAll();
        //Then
        assertNotNull(list);
        assertEquals(list.size(),1);
    }

    @Test
    public void test_getUndoneTasks(){
        //given
        var undone = Task.builder().id(1L).label("undone").complete(false).build();
        when(repository.findByComplete(false)).thenReturn(Collections.singletonList(undone));
        //When
        List<Task> list = service.getUndoneTasks();
        //Then
        assertNotNull(list);
        assertEquals(list.size(),1);
    }

    @Test
    public void test_addNewTask(){
        //given
        var undone = Task.builder().id(1L).label("undone").complete(false).build();
        //When
        service.addNewTask(undone);
        //Then
        verify(repository).save(captor.capture());
        var task = captor.getValue();
        assertNotNull(task);
    }

    @Test
    public void test_getById_valid(){
        //given
        var undone = Task.builder().id(1L).label("undone").complete(false).build();
        when(repository.findById(1L)).thenReturn(Optional.of(undone));
        //When
        var task = service.getTask(1L);
        //Then
        assertNotNull(task);
    }

    @Test
    public void test_getById_NotFound() {
        assertThrows(ResponseStatusException.class,() -> service.getTask(1L));
    }

    @Test
    public void test_updateStatus_valid(){
        //given
        var request = TaskStatusDTO.builder().newStatus(true).build();
        var storedTask = Task.builder().id(1L).label("test").complete(false).build();

        when(repository.findById(1L)).thenReturn(Optional.of(storedTask));

        //When
        service.updateTaskStatus(1L,request);

        //Then
        verify(repository).save(captor.capture());
        var savedTask = captor.getValue();
        assertNotNull(savedTask);
        assertTrue(savedTask.isComplete());
    }

    @Test
    public void test_updateStatus_NotFound() {
        assertThrows(ResponseStatusException.class,() -> service.updateTaskStatus(1L, null));
    }

}
