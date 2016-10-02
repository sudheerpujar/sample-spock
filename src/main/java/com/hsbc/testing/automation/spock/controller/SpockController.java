package com.hsbc.testing.automation.spock.controller;


import com.hsbc.testing.automation.spock.bean.RestSpock;
import com.hsbc.testing.automation.spock.bean.Spock;
import com.hsbc.testing.automation.spock.builder.impl.SpockBuilder;
import com.hsbc.testing.automation.spock.service.SpockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by sudhe on 01-08-2016.
 */
@Controller
@RequestMapping("/spocks")
public class SpockController {
    private SpockService spockService;
    private SpockBuilder spockBuilder;
    @Autowired
    public SpockController(SpockService spockService,SpockBuilder spockBuilder){
        this.spockService=spockService;
        this.spockBuilder=spockBuilder;
    }

    @GetMapping(path="/status")
    public @ResponseBody String getStatus(){ return "Running";}

    @GetMapping(path = "/all")
    public @ResponseBody List<Spock> getAll(){
        return spockService.getAll();
    }


    @PostMapping(path = "/create")
    public @ResponseBody boolean createSpock(@RequestBody RestSpock restSpock){
        Spock spock=spockBuilder.id(restSpock.getId())
                .name(restSpock.getName())
                .emailId(restSpock.getEmailId())
                .date(restSpock.getDate())
                .amount(restSpock.getAmount())
                .build();
        spockService.createSpock(spock);
        return true;
    }

    @PostMapping(path = "/modify")
    public @ResponseBody boolean modifySpock(@RequestBody RestSpock restSpock){
        Spock spock=spockBuilder.id(restSpock.getId())
                .name(restSpock.getName())
                .emailId(restSpock.getEmailId())
                .date(restSpock.getDate())
                .amount(restSpock.getAmount())
                .build();
        spockService.modifySpock(spock);
        return true;
    }

    @GetMapping(path = "/get/{id}")
    public @ResponseBody RestSpock getSpockById(@PathVariable("id") int id){
        Spock spock=spockService.getById(id);
        RestSpock restSpock = new RestSpock(spock.getId(),spock.getName(),spock.getEmailId(),spock.getDate(),spock.getAmount());
        return restSpock;
    }

    @DeleteMapping(path = "/remove/{id}")
    public @ResponseBody boolean modifySpock(@PathVariable("id") int id){
        spockService.removeSpock(id);
        return true;
    }
}
