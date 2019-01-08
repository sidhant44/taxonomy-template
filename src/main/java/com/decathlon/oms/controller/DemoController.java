package com.ge.grid.controller;

import com.ge.grid.config.exception.BadRequestException;
import com.ge.grid.config.exception.ResourceNotFoundException;
import com.ge.grid.logging.Log;
import com.ge.grid.logging.LogExecutionTime;
import com.ge.grid.model.MyModel;
import com.ge.grid.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.net.ConnectException;
import java.util.HashMap;

@EnableCircuitBreaker
@SpringBootApplication
@RestController
public class DemoController {

    @Autowired
    MyService service;

    @Log
    @GetMapping(value = "/get/{name}",produces = MediaType.APPLICATION_JSON,consumes = MediaType.APPLICATION_JSON)
    @LogExecutionTime
    public ResponseEntity<MyModel> getData(@PathVariable String name) throws ConnectException {
       // return ResponseEntity.ok(service.getData(name));
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.set("token","hgfkjghfkjghfdgkjfdhgdf");
        httpHeaders.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(service.getData(name),httpHeaders,HttpStatus.OK);
    }

    @PostMapping(value = "/post",produces = MediaType.APPLICATION_JSON,consumes = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    @LogExecutionTime
    public MyModel postData(@RequestBody MyModel myModel){
        if (true)
            throw new ResourceNotFoundException("resource nahi mila");
        myModel.setMessage("data saved for:"+myModel.getMessage());
        return myModel;
    }

    @PutMapping(value = "/put",produces = MediaType.APPLICATION_JSON,consumes = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @LogExecutionTime
    public MyModel putData(@RequestBody MyModel myModel){
        myModel.setMessage("data updated for:"+myModel.getMessage());
        return myModel;
    }

}
