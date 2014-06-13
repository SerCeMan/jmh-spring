package ru.serce.benchtest;

import org.openjdk.jmh.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import ru.serce.jmhspring.SpringContext;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class TestBenchmark {

    @Autowired
    SimpleService service;

    @Setup
    public void init() {
        SpringContext.autowireBean(this);
    }

//    @Benchmark
//    public SimpleService empty() {
//        return service;
//    }

//    @Benchmark
//    public SimpleService fromFactoryByClass() {
//        return context.getBean(SimpleService.class);
//    }
//
//    @Benchmark
//    public SimpleService fromFactoryByName() {
//        return (SimpleService) context.getBean("simpleService");
//    }
//
//    @Benchmark
//    public SimpleService fromFactoryByNameAndClass() {
//        return context.getBean("simpleService", SimpleService.class);
//    }

    @Benchmark
    public void test1() {
        service.doWork();
    }

//    public static void main(String[] args) throws RunnerException {
//        context = new FileSystemXmlApplicationContext("file:/home/serce/git/jmh-spring/src/main/resources/applicationContext.xml");
//        System.out.println("init");
//
//        Options opt = new OptionsBuilder()
//                .include(".*" + TestBenchmark.class.getSimpleName() + ".*")
//                .forks(1)
//                .warmupIterations(5)
//                .measurementIterations(5)
//                .build();
//
//        new Runner(opt).run();
//    }
}
