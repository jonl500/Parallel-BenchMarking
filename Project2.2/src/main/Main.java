package main;

//package com.blocking-Vs-NonBlocking.jmh;


import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Phaser;

public class Main {

   // @Benchmark
    public static void main(String[] args) {
       // NonBlockingBenchmark benchmark = new NonBlockingBenchmark();
       // benchmark.testBlocking();
       // benchmark.test2ThreadsNonBlocking();
//        BlockingDataStructure bds = new BlockingDataStructure();
//        bds.setUpClasses();
//        BlockingStudent BlockingStudent = new BlockingStudent("Student " + Thread.currentThread().getId(), (int) Thread.currentThread().getId(), bds);
//        BlockingStudent.run();


       //NonBlockingDataStructure nbds = new NonBlockingDataStructure();

        BlockingDataStructure bds = new BlockingDataStructure();
        bds.setUpClasses();
        final Phaser phaser = new Phaser() {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println("Phase: " + phase);
                return phase >= 100 || registeredParties == 0;
            }
        };
        phaser.register();
        //nbds.setUpClasses();

        int phase = phaser.getPhase();
        int proc = Runtime.getRuntime().availableProcessors();

//        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors(),
//                ForkJoinPool.defaultForkJoinWorkerThreadFactory,
//                null, true);
        System.out.println(proc);
        for (int i = 0; i < proc; i++) {
           // int phaseCheck = phaser.getPhase();
//            phaser.register();
//            System.out.println("Current phase: "+phase);
//            forkJoinPool.submit(
//                    new Thread() {
//                        final nonBlockingStudent nonBlockingStudent = new nonBlockingStudent("Student " + currentThread().getId(), (int)currentThread().getId(), nbds);
//
//                        public void run() {
//                            do {
//
//                                nonBlockingStudent.run();
//
//                                phaser.arriveAndAwaitAdvance();
//
//                            } while (!phaser.isTerminated());
//
//                        }
//
//                    });
//
//            phaser.arriveAndDeregister();
           // System.out.println("Current phase: "+phase);
            phaser.register();
//            forkJoinPool.submit(
                    new Thread() {
                        final BlockingStudent blockingStudent = new BlockingStudent("Student " + currentThread().getId(), (int)currentThread().getId(), bds);

                        public void run() {
                            do {
                                blockingStudent.run();
                                phaser.arriveAndAwaitAdvance();


                            } while (!phaser.isTerminated());

                        }

                    }.start();//);



        }
        phaser.arriveAndDeregister();



    }
}

