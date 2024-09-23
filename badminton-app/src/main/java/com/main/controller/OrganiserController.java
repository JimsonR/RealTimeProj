package com.main.controller;

import com.main.service.OrganiserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrganiserController {
    @Autowired
    OrganiserAccountService organiserAccountService;

    @PostMapping("/org")
    public int addMember(){

        return organiserAccountService.removeMember();

    }







}
