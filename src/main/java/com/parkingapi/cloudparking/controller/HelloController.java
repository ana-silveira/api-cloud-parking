package com.parkingapi.cloudparking.controller;

/*
Classe de retorno de String "Hello".
Quando eu for no browser e digitar o localhost:8080,
a mensagem será disparada nesse endpoint.
*/

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping ("/")
@ApiIgnore
public class HelloController {

    @GetMapping
    public String hello(){
        return "HELLO . Java Devs";
    }
}
