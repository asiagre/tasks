package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
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
public class TaskControllerTest {

    @InjectMocks
    private TaskController taskController;

    @Mock
    private DbService dbService;

    @Mock
    private TaskMapper taskMapper;

    @Test
    public void shouldGetTasks() {
        //Given
        Task task1 = new Task(1L, "name", "description");
        Task task2 = new Task(2L, "second name", "second description");
        TaskDto taskDto1 = new TaskDto(1L, "name", "description");
        TaskDto taskDto2 = new TaskDto(2L, "second name", "second description");
        dbService.saveTask(task1);
        dbService.saveTask(task2);
        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(taskDto1);
        taskDtoList.add(taskDto2);
        when(dbService.getAllTasks()).thenReturn(taskList);
        when(taskMapper.mapToTaskDtoList(taskList)).thenReturn(taskDtoList);

        //When
        List<TaskDto> taskDtos = taskController.getTasks();

        //Then
        assertEquals(2, taskDtos.size());
    }

    @Test
    public void shouldGetTask() {
        //Given
        Task task = new Task(1L, "name", "description");
        TaskDto taskDto = new TaskDto(1L, "name", "description");
        when(dbService.getTask(1L)).thenReturn(Optional.of(task));
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //When
        try {
            TaskDto taskDto1 = taskController.getTask(1L);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Then
        Assert.assertEquals("name", taskDto.getTitle());
    }

}