package org.example;

import org.example.annotation.Controller;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;

import java.util.HashSet;
import java.util.Set;

/**
 * @Controller 어노테이션이 설정되어있는 모든 클래스를 찾아서 출력한다
 */
public class ReflectionTest {
  @Test
  void controllerScan(){
    Reflections reflections=new Reflections("org.example");

    Set<Class<?>> beans=new HashSet<>();
    beans.addAll(reflections.getTypesAnnotatedWith(Controller.class));

  }
}
