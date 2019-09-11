package com.tavisca.workspaces.ToDo.RestAPI.contoller;

import com.tavisca.workspaces.ToDo.RestAPI.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

public class MasterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Service todoService;

    public MasterControllerTest(){
        
    }
}
