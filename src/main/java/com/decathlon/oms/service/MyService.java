package com.ge.grid.service;

import com.ge.grid.model.MyModel;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;

import java.net.ConnectException;

@Service
public class MyService {

    @HystrixCommand(fallbackMethod = "reliable")
    public MyModel getData(String name) {
        MyModel myModel=new MyModel();
            try {
                if (true)
                throw new ConnectException();
                myModel.setMessage("Hi There! "+name);
                return myModel;

            } catch (ConnectException e) {
                MyModel myModel1=new MyModel();
                myModel1.setMessage("Hi There! "+"system is down: catch");
                return myModel1;
            }
    }

    public MyModel reliable(String name){
        MyModel myModel=new MyModel();
        myModel.setMessage("Hi There! "+"system is down");
        return myModel;
    }
}
