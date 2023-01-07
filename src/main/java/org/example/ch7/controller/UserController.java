package org.example.ch7.controller;

import org.example.ch7.annotation.Controller;
import org.example.ch7.annotation.Inject;
import org.example.ch7.service.UserService;

@Controller
public class UserController {
    private final UserService userService;

    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService() {
        return userService;
    }
}
