package org.example.ch6.reflection.controller;

import org.example.ch6.reflection.annotation.Controller;
import org.example.ch6.reflection.annotation.RequestMapping;
import org.example.ch6.reflection.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HealthCheckController {
    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public String health(HttpServletRequest request, HttpServletResponse response) {
        return "health";
    }
}
