package ru.serce.benchtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Sergey Tselovalnikov
 * @since 12.06.2014
 */
@Component
public class SimpleService {

    @Autowired
    WorkerService workerService;

    public long doWork() {
        return workerService.work();
    }
}
