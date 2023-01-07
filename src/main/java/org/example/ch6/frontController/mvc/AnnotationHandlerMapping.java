package org.example.ch6.frontController.mvc;


import org.example.ch6.frontController.mvc.annotation.Controller;
import org.example.ch6.frontController.mvc.annotation.RequestMapping;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnnotationHandlerMapping implements HandlerMapping {
    private final Object[] basePackage;
    private Map<HandlerKey, AnnotationHandler> handlers = new HashMap<>();

    public AnnotationHandlerMapping(Object... basePackage) {
        this.basePackage = basePackage;
    }

    public void initialize() {
        Reflections reflections = new Reflections(basePackage);

        Set<Class<?>> classesWithControllerAnnotation = reflections.getTypesAnnotatedWith(Controller.class);

        classesWithControllerAnnotation.forEach(c ->
                Arrays.stream(c.getDeclaredMethods()).forEach(declaredMethod -> {
                    RequestMapping requestMapping = declaredMethod.getDeclaredAnnotation(RequestMapping.class);

                    Arrays.stream(getRequestMethods(requestMapping))
                            .forEach(requestMethod -> handlers.put(
                                    new HandlerKey(requestMethod, requestMapping.value()), new AnnotationHandler(c, declaredMethod)
                            ));
                })
        );
    }

    private RequestMethod[] getRequestMethods(RequestMapping requestMapping) {
        return requestMapping.method();
    }

    @Override
    public Object findHandler(HandlerKey handlerKey) {
        return handlers.get(handlerKey);
    }
}
