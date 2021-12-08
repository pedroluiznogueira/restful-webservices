package com.rest.restfulwebservices.controller;

import com.rest.restfulwebservices.model.HelloWorld;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class HelloWorldController {

    @GetMapping
    public String getHello() {
        return "Hello world";
    }

    @GetMapping("bean")
    public HelloWorld getHelloBean() {
        return new HelloWorld("Hello bean");
    }

    @GetMapping("param/{name}")
    public String getHelloParam(@PathVariable ("name") String name) {
        return name;
    }
}
