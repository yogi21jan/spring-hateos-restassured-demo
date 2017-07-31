package com.wyn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/product")
@Api(value="HomeController REST API")
public class HomeController {

    @GetMapping("/{userName}")
    @ApiOperation(value = "Shows greeting msg to user")
    public @ResponseBody String greeting(@PathVariable("userName") String userName) {
        return "Hello World";
    }

}
