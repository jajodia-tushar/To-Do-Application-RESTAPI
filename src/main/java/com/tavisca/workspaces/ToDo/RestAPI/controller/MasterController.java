package com.tavisca.workspaces.ToDo.RestAPI.controller;
import com.tavisca.workspaces.ToDo.RestAPI.dataObjects.Error;
import com.tavisca.workspaces.ToDo.RestAPI.dataObjects.Success;
import com.tavisca.workspaces.ToDo.RestAPI.dataObjects.Task;
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

    List<Task> tasks = new ArrayList<Task>(){{
        add(new Task("0","BootStrapCoding"));
        add(new Task("1","CSS Foundation"));
        add(new Task("2","Advanced JavaScript"));
        add(new Task("3","Java Spring"));
    }};

    @CrossOrigin
    @GetMapping
    public Object returnUsers(){
        if(tasks.isEmpty())
            return new ResponseEntity<Error>(new Error("failed","There is No Content in Database"), HttpStatus.NO_CONTENT);
        return tasks;
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<?> returnNewTask(@RequestBody Task task){
        task.setId(tasks.size()+"");
        if(task.getData().equals(""))
            return new ResponseEntity<Error>(new Error("failed","Task Cannot Be Empty"), HttpStatus.BAD_REQUEST);

        if(tasks.add(task)){
            return new ResponseEntity<Success>(new Success("Success","Added"), HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<Error>(new Error("failed","Some Conflict with previous Data"), HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin
    @PutMapping
    public ResponseEntity<?> returnUpdatedTask(@RequestBody ArrayList<Task> newTasks){
        Task oldTask = newTasks.get(0);
        if(oldTask == null || oldTask.getId() == null){
            return new ResponseEntity<Error>(new Error("failed","The Task being modify doesn't Exists"), HttpStatus.BAD_REQUEST);
        }
        Task newTask = newTasks.get(1);
        if(newTask == null || newTask.getId() == null || newTask.getData().equals("")){
            return new ResponseEntity<Error>(new Error("failed","The new Task Cannot Be Empty"), HttpStatus.BAD_REQUEST);
        }
        if(tasks.remove(oldTask)){
            newTask.setId(oldTask.getId());
            tasks.add(newTask);
            tasks = sortAndReorderingList(tasks);
            return new ResponseEntity<Success>(new Success("Success","Modified"), HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<Error>(new Error("failed","The Task being modify doesn't Exists"), HttpStatus.NO_CONTENT);
        }
    }

    @CrossOrigin
    @DeleteMapping
    public ResponseEntity<?> returnFreshTask(@RequestBody Task oldTask){

        if(oldTask == null || oldTask.getId() == null){
            return new ResponseEntity<Error>(new Error("failed","The Task Being Delete Cannot be Empty"), HttpStatus.BAD_REQUEST);
        }
        if(tasks.remove(oldTask)){
            tasks = sortAndReorderingList(tasks);
            return new ResponseEntity<Success>(new Success("Success","Deleted"), HttpStatus.OK
            );
        }
        else {
            return new ResponseEntity<Error>(new Error("failed","The Task being deleted has Some Conflict"), HttpStatus.NO_CONTENT);
        }
    }

    public static List<Task> sortAndReorderingList(List<Task> tasks){
        tasks.sort(Comparator.comparing(Task::getId));

        ArrayList<Task> newArrayList = new ArrayList<Task>();
        for(Task task : tasks){
            int index = tasks.indexOf(task);
            newArrayList.add(new Task(index+"",task.getData()));
        }
        return newArrayList;


    }



}
