package org.example.ch6.frontController.mvc.controller;

import org.example.ch6.frontController.mvc.RequestMethod;
import org.example.ch6.frontController.mvc.annotation.Controller;
import org.example.ch6.frontController.mvc.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "home";
    }
}
