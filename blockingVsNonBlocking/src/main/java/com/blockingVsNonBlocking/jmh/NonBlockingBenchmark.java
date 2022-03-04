/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.blockingVsNonBlocking.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.*;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class NonBlockingBenchmark {
    @State(Scope.Group)
    public static class ThreadState {
        NonBlockingDataStructure nbds;
       // Phaser phaser;
     //   ForkJoinPool forkJoinPool;
        @Setup
        public void prepare() {
//            phaser = new Phaser() {
//            @Override
//            protected boolean onAdvance(int phase, int registeredParties) {
//                System.out.println("Phase: " + phase);
//                return phase >= 100 || registeredParties == 0;
//            }
//        };
            nbds = new NonBlockingDataStructure();
            nbds.setUpClasses();
//            forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors(),
//                    ForkJoinPool.defaultForkJoinWorkerThreadFactory,
//                    null, true);

        }
        @TearDown
        public void tearDown(){
            nbds = null;
        }
    }

    @Benchmark
    @Fork(value = 1)
    @Measurement(iterations = 10, time = 1)
    @Warmup(iterations = 5, time = 1)
    @Group("twoStudents")
    @GroupThreads(2)
    public void test2ThreadsNonBlocking(ThreadState state) {
//        state.phaser.register();
        final nonBlockingStudent nonBlockingStudent = new nonBlockingStudent("Student " + Thread.currentThread().getId(), (int) Thread.currentThread().getId(), state.nbds);
        nonBlockingStudent.run();
//        int phase = state.phaser.getPhase();
//        int proc = Runtime.getRuntime().availableProcessors();
        //    int phaseCheck = state.phaser.getPhase();
        //    state.phaser.register();
//            System.out.println("Current phase: " + phase);
//            state.forkJoinPool.submit(
//                    new Thread() {
//                        final nonBlockingStudent nonBlockingStudent = new nonBlockingStudent("Student " + currentThread().getId(), (int) currentThread().getId(), state.nbds);
//                        public void run() {
////                            do {
//                                nonBlockingStudent.run();
//                           //     state.phaser.arriveAndAwaitAdvance();
////                            } while (!state.phaser.isTerminated());
//                        }
//                    });
//            state.phaser.arriveAndDeregister();
    }

    @Benchmark
    @Fork(value = 1)
    @Measurement(iterations = 10, time = 1)
    @Warmup(iterations = 5, time = 1)
    @Group("fourStudents")
    @GroupThreads(4)
    public void test4ThreadsNonBlocking(ThreadState state) {

//        state.phaser.register();
   //     state.nbds.setUpClasses();
//        int phase = state.phaser.getPhase();
//        int proc = Runtime.getRuntime().availableProcessors();
//        int phaseCheck = state.phaser.getPhase();
        //state.phaser.register();
//        System.out.println("Current phase: " + phase);
        final nonBlockingStudent nonBlockingStudent = new nonBlockingStudent("Student " + Thread.currentThread().getId(), (int) Thread.currentThread().getId(), state.nbds);
        nonBlockingStudent.run();
//        state.forkJoinPool.submit(
//                new Thread() {
//                    final nonBlockingStudent nonBlockingStudent = new nonBlockingStudent("Student " + currentThread().getId(), (int) currentThread().getId(), state.nbds);
//
//                    public void run() {
////                        do {
//
//                            nonBlockingStudent.run();
//
//                     //       state.phaser.arriveAndAwaitAdvance();
//
////                        } while (!state.phaser.isTerminated());
//
//                    }
//
//                });
//        state.phaser.arriveAndDeregister();
    }
    @Benchmark
    @Fork(value = 1)
    @Measurement(iterations = 10, time = 1)
    @Warmup(iterations = 5, time = 1)
    @Group("EightStudents")
    @GroupThreads(8)
    public void test8ThreadsNonBlocking(ThreadState state) {

//        state.phaser.register();
    //    state.nbds.setUpClasses();
//        int phase = state.phaser.getPhase();
//        int proc = Runtime.getRuntime().availableProcessors();
//        int phaseCheck = state.phaser.getPhase();
//        state.phaser.register();
//        System.out.println("Current phase: " + phase);
        final nonBlockingStudent nonBlockingStudent = new nonBlockingStudent("Student " + Thread.currentThread().getId(), (int) Thread.currentThread().getId(), state.nbds);
        nonBlockingStudent.run();
//        state.forkJoinPool.submit(
//                new Thread() {
//                    final nonBlockingStudent nonBlockingStudent = new nonBlockingStudent("Student " + currentThread().getId(), (int) currentThread().getId(), state.nbds);
//
//                    public void run() {
////                        do {
//
//                            nonBlockingStudent.run();
//
//    //                        state.phaser.arriveAndAwaitAdvance();
////                        } while (!state.phaser.isTerminated());
//                    }
//                });
     //   state.phaser.arriveAndDeregister();
    }
    @Benchmark
    @Fork(value = 1)
    @Measurement(iterations = 10, time = 1)
    @Warmup(iterations = 5, time = 1)
    @Group("SixteenStudents")
    @GroupThreads(16)
    public void test16ThreadsNonBlocking(ThreadState state) {
//        state.phaser.register();
    //    state.nbds.setUpClasses();
//        int phase = state.phaser.getPhase();
//        int proc = Runtime.getRuntime().availableProcessors();
//        int phaseCheck = state.phaser.getPhase();
//        state.phaser.register();
//        System.out.println("Current phase: " + phase);
        final nonBlockingStudent nonBlockingStudent = new nonBlockingStudent("Student " + Thread.currentThread().getId(), (int) Thread.currentThread().getId(), state.nbds);
        nonBlockingStudent.run();
//        state.forkJoinPool.submit(
//                new Thread() {
//                    final nonBlockingStudent nonBlockingStudent = new nonBlockingStudent("Student " + currentThread().getId(), (int) currentThread().getId(), state.nbds);
//
//                    public void run() {
////                        do {
//
//                            nonBlockingStudent.run();
//
//           //                 state.phaser.arriveAndAwaitAdvance();
//
////                        } while (!state.phaser.isTerminated());
//                    }
//                });
//        state.phaser.arriveAndDeregister();
    }
    @Benchmark
    @Fork(value = 2)
    @Measurement(iterations = 10, time = 1)
    @Warmup(iterations = 5, time = 1)
    @Group("ThirtyTwoStudents")
    @GroupThreads(32)
    public void test32ThreadsNonBlocking(ThreadState state) {

      //  state.phaser.register();
  //      state.nbds.setUpClasses();
        //int phase = state.phaser.getPhase();
//        int proc = Runtime.getRuntime().availableProcessors();
//        int phaseCheck = state.phaser.getPhase();
        //state.phaser.register();
//        System.out.println("Current phase: " + phase);
        final nonBlockingStudent nonBlockingStudent = new nonBlockingStudent("Student " + Thread.currentThread().getId(), (int) Thread.currentThread().getId(), state.nbds);
        nonBlockingStudent.run();
//        state.forkJoinPool.submit(
//                new Thread() {
//                    final nonBlockingStudent nonBlockingStudent = new nonBlockingStudent("Student " + currentThread().getId(), (int) currentThread().getId(), state.nbds);
//                    public void run() {
////                        do {
//                            nonBlockingStudent.run();
////                            state.phaser.arriveAndAwaitAdvance();
////                        } while (!state.phaser.isTerminated());
//                    }
//                });
//        state.phaser.arriveAndDeregister();
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(NonBlockingBenchmark.class.getSimpleName())
                .warmupIterations(5)
                .warmupTime(TimeValue.seconds(10))
                .measurementIterations(5)
                .forks(3)
                .result("nbResults.json")
                .resultFormat(ResultFormatType.JSON)
                .build();
        new Runner(opt).run();

    }
}
