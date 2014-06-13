package ru.serce.jmhspring;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;

/**
 * @author Sergey Tselovalnikov
 * @since 13.06.2014
 */
public class SpringContext {

    private static volatile ApplicationContext context;

    public static void setContext(ApplicationContext context) {
        SpringContext.context = context;
    }

    public static void autowireBean(Object bean) {
        AutowireCapableBeanFactory factory = context.getAutowireCapableBeanFactory();
        factory.autowireBean(bean);
    }
}
