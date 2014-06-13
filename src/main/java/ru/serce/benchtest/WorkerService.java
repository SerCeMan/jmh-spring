package ru.serce.benchtest;

import org.springframework.stereotype.Component;

import java.util.Random;

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
        a++;
        a++;
        a++;
        a++;
        a++;
        a++;
        a++;
        a++;
        a++;
        if(new Random().nextInt() == 104342) {
            System.out.println(a);
        }
        return a;
    }
}
