package com.rest.restfulwebservices.controller;

import com.rest.restfulwebservices.model.HelloWorld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("hello")
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

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
        return "Hello, " + name;
    }

    @GetMapping("hello-world-internationalization")
    public String getHelloInternationalization(
            @RequestHeader(name = "Accept-Language", required = false) Locale locale
    ) {
        return messageSource
                .getMessage("good.morning.message", null, "Default Message", locale);
    }

}
