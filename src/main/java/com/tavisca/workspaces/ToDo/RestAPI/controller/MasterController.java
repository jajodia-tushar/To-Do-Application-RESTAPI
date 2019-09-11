package com.tavisca.workspaces.ToDo.RestAPI.controller;
import com.tavisca.workspaces.ToDo.RestAPI.dataObjects.Error;
import com.tavisca.workspaces.ToDo.RestAPI.dataObjects.Success;
import com.tavisca.workspaces.ToDo.RestAPI.dataObjects.Task;
import com.tavisca.workspaces.ToDo.RestAPI.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping (MasterController.URL)
public class MasterController {

    public static final String URL = "api/to-do/tasks";

    @Autowired
    private Service todoData;

    @CrossOrigin
    @GetMapping
    public Object getAllTheTasks(){
        System.out.println("I am Here");
        if(todoData.isEmpty())
            return new ResponseEntity<Error>(new Error("failed","There is No Content in Database"), HttpStatus.NO_CONTENT);

        return todoData.findAll();
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<?> addNewTask(@RequestBody Task task){
        if(task == null || task.getData().equals(""))
            return new ResponseEntity<Error>(new Error("failed","Task Cannot Be Empty"), HttpStatus.BAD_REQUEST);
        if(this.todoData.add(task)){
            return new ResponseEntity<Success>(new Success("Success","Added"), HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<Error>(new Error("failed","Some Conflict with previous Data"), HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin
    @PutMapping
    public ResponseEntity<?> updateOldTask(@RequestBody ArrayList<Task> newTasks){
        Task oldTask = newTasks.get(0);
        if(oldTask == null || oldTask.getId() == null){
            return new ResponseEntity<Error>(new Error("failed","The Task being modify doesn't Exists"), HttpStatus.BAD_REQUEST);
        }
        Task newTask = newTasks.get(1);
        if(newTask == null || newTask.getId() == null || newTask.getData().equals("")){
            return new ResponseEntity<Error>(new Error("failed","The new Task Cannot Be Empty"), HttpStatus.BAD_REQUEST);
        }
        if(this.todoData.contains(oldTask) && (this.todoData.update(oldTask,newTask))){
            return new ResponseEntity<Success>(new Success("Success","Modified"), HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<Error>(new Error("failed","The Task being modify doesn't Exists"), HttpStatus.NO_CONTENT);
        }
    }

    @CrossOrigin
    @DeleteMapping
    public ResponseEntity<?> deleteOldTask(@RequestBody Task oldTask){
        if(oldTask == null || oldTask.getId() == null){
            return new ResponseEntity<Error>(new Error("failed","The Task Being Delete Cannot be Empty"), HttpStatus.BAD_REQUEST);
        }
        if(this.todoData.contains(oldTask) && this.todoData.remove(oldTask)){
            return new ResponseEntity<Success>(new Success("Success","Deleted"), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<Error>(new Error("failed",
                    "The Task being deleted has Some Conflict"), HttpStatus.NO_CONTENT);
        }
    }





}
