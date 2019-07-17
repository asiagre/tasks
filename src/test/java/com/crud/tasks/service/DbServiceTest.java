package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void shouldGetAllTasks() {
        //Given
        Task task1 = new Task(1L, "name", "description");
        Task task2 = new Task(2L, "second name", "second description");
        Task task3 = new Task(3L, "third name", "third description");
        dbService.saveTask(task1);
        dbService.saveTask(task2);
        dbService.saveTask(task3);
        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        when(taskRepository.findAll()).thenReturn(taskList);

        //When
        List<Task> taskListFromDb = dbService.getAllTasks();

        //Then
        assertEquals(3, taskListFromDb.size());
    }

    @Test
    public void shouldGetEmptyTaskList() {
        //Given
        when(taskRepository.findAll()).thenReturn(new ArrayList<>());

        //When
        List<Task> taskListFromDb = dbService.getAllTasks();

        //Then
        assertEquals(0, taskListFromDb.size());
    }

    @Test
    public void shouldGetTask() {
        //Given
        Task task = new Task(1L, "name", "description");
        when(taskRepository.save(task)).thenReturn(task);
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        dbService.saveTask(task);

        //When
        Task taskFromDb = dbService.getTask(1L).get();

        //Then
        Assert.assertEquals("name", taskFromDb.getTitle());
    }

    @Test
    public void shouldSaveTask() {
        //Given
        Task task = new Task(1L, "name", "description");
        when(taskRepository.save(task)).thenReturn(task);
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        //When
        dbService.saveTask(task);
        Task taskFromDb = dbService.getTask(1L).get();

        //Then
        Assert.assertEquals("name", taskFromDb.getTitle());
    }

    @Test
    public void shouldDeleteTask() {
        //Given
        Task task = new Task(1L, "name", "description");
        when(taskRepository.findById(1L)).thenReturn(null);

        //When
        dbService.deleteTask(1L);
        Optional<Task> taskFromDb = dbService.getTask(1L);

        //Then
        Assert.assertNull(taskFromDb);
    }

}