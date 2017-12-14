package com.wyn.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/product")
@Api(value="HomeController REST API")
public class HomeController {

    @GetMapping("/product/{userName}")
    @ApiOperation(value = "Shows greeting msg to user")
    public @ResponseBody String greeting(@PathVariable("userName") String userName) {
        return "Hello World";
    }
    
    @ApiOperation(value = "fetch")
	@PostMapping(produces = MediaType.ALL_VALUE)
	public ResponseEntity<String> fetch(){
	        return  new ResponseEntity<String>("Depricated you should use v1.0.1...", HttpStatus.NOT_ACCEPTABLE);
	    }

}
