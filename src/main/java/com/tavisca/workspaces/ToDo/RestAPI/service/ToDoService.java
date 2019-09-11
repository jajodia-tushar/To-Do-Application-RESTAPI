package com.tavisca.workspaces.ToDo.RestAPI.service;

import com.tavisca.workspaces.ToDo.RestAPI.dataObjects.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class ToDoService implements Service {

    private List<Task> tasks = new ArrayList<>();

    @Override
    public boolean add(Task task) {
        return this.tasks.add(task);
    }

    @Override
    public boolean remove(Task task) {
         if(this.tasks.remove(task)){
             this.sortAndReorderingList();
             return true;
         }
         return false;
    }

    @Override
    public List<Task> findAll() {
        return this.tasks;
    }

    @Override
    public boolean isEmpty(){
        return this.tasks.isEmpty();
    }

    @Override
    public boolean contains(Task task){
        return this.tasks.contains(task);
    }

    private void sortAndReorderingList(){
        this.tasks.sort(Comparator.comparing(Task::getId));
        ArrayList<Task> newArrayList = new ArrayList<Task>();
        for(Task task : this.tasks){
            int index = this.tasks.indexOf(task);
            newArrayList.add(new Task(index+"",task.getData()));
        }
        this.tasks = newArrayList;
    }

    @Override
    public boolean update(Task oldTask, Task newTask) {
        if(this.tasks.remove(oldTask) && this.tasks.add(newTask)){
            this.sortAndReorderingList();
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return this.tasks.size();
    }
}
