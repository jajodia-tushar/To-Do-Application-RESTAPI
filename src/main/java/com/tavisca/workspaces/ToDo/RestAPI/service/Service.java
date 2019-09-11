package com.tavisca.workspaces.ToDo.RestAPI.service;

import com.tavisca.workspaces.ToDo.RestAPI.dataObjects.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public interface Service {
    boolean add(Task task);
    boolean remove(Task task);
    List<Task> findAll();
    boolean isEmpty();
    boolean contains(Task task);
    boolean update(Task oldTask, Task newTask);
    int size();
}
