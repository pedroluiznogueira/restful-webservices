package com.rest.restfulwebservices.controller;

import com.rest.restfulwebservices.model.Name;
import com.rest.restfulwebservices.model.PersonV1;
import com.rest.restfulwebservices.model.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("person")
public class PersonVersioningController {

    @GetMapping(value = "param", params = "1")
    public String personV1() {
        return new PersonV1("Pedro").getName();
    }

    @GetMapping(value = "param", params = "2")
    public Name personV2() {
        return new PersonV2(new Name("Pedro", "Luiz")).getName();
    }
}
