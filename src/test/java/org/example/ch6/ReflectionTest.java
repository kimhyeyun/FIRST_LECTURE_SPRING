package org.example.ch6;

import org.example.ch6.annotation.Controller;
import org.example.ch6.annotation.Service;
import org.example.ch6.model.User;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/*
 * @Controller 애노테이션이 설정돼 있는 모든 클래스를 찾아서 출력한다.
 * */
public class ReflectionTest {

    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    private static Set<Class<?>> getTypesAnnotatedWith(List<Class<? extends Annotation>> annotations) {
        Reflections reflections = new Reflections("org.example.ch6");

        Set<Class<?>> beans = new HashSet<>();
        annotations.forEach(annotation -> beans.addAll(reflections.getTypesAnnotatedWith(annotation)));

        return beans;
    }

    @Test
    void controllerScan() {
        Set<Class<?>> beans = getTypesAnnotatedWith(List.of(Controller.class, Service.class));

        logger.debug("beans: [{}]", beans);
    }

    @Test
    void showClass() {
        Class<User> userClass = User.class;
        logger.debug(userClass.getName());

        logger.debug("User all declared fields: [{}]", Arrays.stream(userClass.getDeclaredFields()).collect(Collectors.toList()));
        logger.debug("User all declared constructors: [{}]", Arrays.stream(userClass.getDeclaredConstructors()).collect(Collectors.toList()));
        logger.debug("User all declared methods: [{}]", Arrays.stream(userClass.getDeclaredMethods()).collect(Collectors.toList()));
    }

    @Test
    void load() throws ClassNotFoundException {
        Class<User> userClass = User.class;

        User user = new User("test", "김혜윤");
        Class<? extends User> userClass2 = user.getClass();

        Class<?> userClass3 = Class.forName("org.example.ch6.model.User");

        logger.debug("class: [{}]", userClass);
        logger.debug("class2: [{}]", userClass2);
        logger.debug("class3: [{}]", userClass3);

        assertThat(userClass == userClass2).isTrue();
        assertThat(userClass == userClass3).isTrue();
        assertThat(userClass3 == userClass2).isTrue();
    }
}
