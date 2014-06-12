package ru.serce.benchtest;

import org.springframework.stereotype.Component;

/**
 * @author Sergey Tselovalnikov
 * @since 12.06.2014
 */
@Component
public class WorkerService {

    public int b = 4;

    public long work() {
        int a = 1;
        for (int i = 0; i < 1000; i++) {
            a += b * a;
        }
        System.out.println("Hello");
        return a;
    }
}
