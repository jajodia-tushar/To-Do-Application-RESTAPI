
package com.tavisca.workspaces.ToDo.RestAPI.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tavisca.workspaces.ToDo.RestAPI.dataObjects.Success;
import com.tavisca.workspaces.ToDo.RestAPI.dataObjects.Task;
import com.tavisca.workspaces.ToDo.RestAPI.service.Service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest

public class MasterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Service todoService;

    private ArrayList<Task> tasksList;

    @Test
    public void willReturnAllTaskWhenHitWithGet() throws Exception {
        this.tasksList = new ArrayList<>();
        tasksList.add(new Task("1","Learn Bootstrap"));
        given(todoService.findAll()).willReturn(this.tasksList);
        this.mockMvc.perform(get("/api/to-do/tasks"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id':'1','data':'Learn Bootstrap'}]"));
    }

    @Test
    public void willReturnSuccessIfTaskIsAdded() throws Exception {
        Task task = new Task("2","Hello");
        given(todoService.add(task)).willReturn(true);
        this.mockMvc.perform(post("/api/to-do/tasks")
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(task)))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"message\":\"Success\",\"description\":\"Added\"}"));
    }

    @Test
    public void willReturnSuccessIfTheTaskIsUpdated() throws Exception {
        Task oldTask = new Task("1","Learn Bootstrap");
        Task newTask = new Task("1","Learn Bootstrap And JS");

        given(todoService.contains(oldTask)).willReturn(true);
        given(todoService.update(oldTask,newTask)).willReturn(true);


        this.mockMvc.perform(put("/api/to-do/tasks")
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(new ArrayList<Task>()
                {{
                    add(oldTask);
                    add(newTask);
                }})))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"message\":\"Success\",\"description\":\"Modified\"}"));
    }

    @Test
    public void willReturnSuccessIfTheTaskIsDeleted() throws Exception {
        Task oldTask = new Task("1","Learn Bootstrap");

        given(todoService.contains(oldTask)).willReturn(true);
        given(todoService.remove(oldTask)).willReturn(true);


        this.mockMvc.perform(delete("/api/to-do/tasks")
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(oldTask)))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"message\":\"Success\",\"description\":\"Deleted\"}"));
    }


}
