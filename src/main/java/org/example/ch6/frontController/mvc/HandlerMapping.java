package org.example.ch6.frontController.mvc;

import org.example.ch6.frontController.mvc.controller.Controller;

public interface HandlerMapping {
    Object findHandler(HandlerKey handlerKey);
}
