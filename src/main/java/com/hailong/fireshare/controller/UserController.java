package com.hailong.fireshare.controller;

import com.hailong.fireshare.entity.RestResult;
import com.hailong.fireshare.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    UserService userService;
}
