package com.tavisca.workspaces.ToDo.RestAPI.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (MasterController.URL)
public class MasterController {
    public static final String URL = "api/v2/users";

    @GetMapping
    public String returnUsers(){
        return "Jajodia";
    }
}
