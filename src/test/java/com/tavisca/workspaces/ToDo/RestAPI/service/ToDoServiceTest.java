package com.tavisca.workspaces.ToDo.RestAPI.service;

import com.tavisca.workspaces.ToDo.RestAPI.dataObjects.Task;
import org.junit.Before;
import org.junit.Test;

import javax.validation.constraints.AssertTrue;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoServiceTest {
    private ToDoService toDoService;

    @Before
    public void initializer(){
        toDoService = new ToDoService();
    }

    @Test
    public void canAddTask(){
        Task task = new Task("1","SEO");

        boolean expectedStatus = true;
        boolean actualStatus = toDoService.add(task);
        assertEquals(expectedStatus,actualStatus);
    }

    @Test
    public void canRemoveTask(){
        Task task1 = new Task("1","SEO");
        Task task2 = new Task("1","DAO");
        this.toDoService.add(task1);
        this.toDoService.add(task2);

        boolean expectedStatus = true;
        boolean actualStatus = toDoService.remove(task1);
        assertEquals(expectedStatus,actualStatus);
    }

    @Test
    public void canUpdateTask(){
        Task task1 = new Task("1","SEO");
        Task task2 = new Task("1","DONT");

        Task task3 = new Task("1","DAO");

        this.toDoService.add(task1);
        this.toDoService.add(task2);

        boolean updatedStatus = toDoService.update(task2,task3);
        assertTrue(updatedStatus);

        boolean containsOldValue = toDoService.contains(task2);
        assertFalse(containsOldValue);

        boolean containsNewValue = toDoService.contains(task3);
        assertTrue(containsNewValue);
    }

    @Test
    public void canReturnIfItIsEmptyOrNot(){

        boolean emptyStatus = this.toDoService.isEmpty();
        assertTrue(emptyStatus);

        Task task1 = new Task("1","SEO");
        this.toDoService.add(task1);

        boolean statusAfterAdding = this.toDoService.isEmpty();
        assertTrue(statusAfterAdding);
    }

    @Test
    public void canSortAndReorder(){

        Task task1 = new Task("0","SEO");
        Task task2 = new Task("1","Boot");
        Task task3 = new Task("2","Shoes");
        Task task4 = new Task("3","Mobile");
        Task task5 = new Task("4","Laptop");

        this.toDoService.add(task1);
        this.toDoService.add(task2);
        this.toDoService.add(task3);
        this.toDoService.add(task4);
        this.toDoService.add(task5);

        this.toDoService.remove(task3);

        List<Task> updatedList = this.toDoService.findAll();

        List<Task> expectedList = new ArrayList<Task>() {{
            add(new Task("0", "SEO"));
            add(new Task("1", "Boot"));
            add(new Task("2", "Mobile"));
            add(new Task("3", "Laptop"));
        }};

        assertTrue(updatedList.equals(expectedList));
    }










}
