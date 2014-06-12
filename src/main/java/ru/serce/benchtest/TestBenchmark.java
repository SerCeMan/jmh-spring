package ru.serce.benchtest;

import org.openjdk.jmh.annotations.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class TestBenchmark
{
    ApplicationContext context;
    SimpleService service;

    @Setup
    public void init() {
//        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        context = new FileSystemXmlApplicationContext("file:/home/serce/git/jmh-spring/src/main/resources/applicationContext.xml");
        service = context.getBean(SimpleService.class);
    }


    @Benchmark
    public void test1() {
        service.doWork();
    }
}
