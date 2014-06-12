package ru.serce.benchtest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Sergey Tselovalnikov
 * @since 12.06.2014
 */
public class TestApp {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        SimpleService service = context.getBean(SimpleService.class);
        service.doWork();
    }

}
