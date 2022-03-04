package test;
import main.*;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class BlockingBenchmark {

    @State(Scope.Group)
    public static class ThreadState {
        BlockingDataStructure bds;
      //  ForkJoinPool forkJoinPool;
//        Phaser phaser;
        @Setup
        public void prepare() {
            bds = new BlockingDataStructure();
            bds.setUpClasses();
//            phaser = new Phaser() {
//                @Override
//                protected boolean onAdvance(int phase, int registeredParties) {
//                    System.out.println("Phase: " + phase);
//                    return phase >= 100 || registeredParties == 0;
//                }
//            };
//            forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors(),
//                    ForkJoinPool.defaultForkJoinWorkerThreadFactory,
//                    null, true);
        }
        @TearDown
        public void tearDown(){
            bds = null;
        }
    }
    //BlockingDataStructure bds = new BlockingDataStructure();

    @Benchmark
    @Fork(value = 1)
    @Measurement(iterations = 10, time = 1)
    @Warmup(iterations = 5, time = 1)
    @Group("twoStudents")
    @GroupThreads(2)
    public void testBlocking2Threads(ThreadState state) {
        // This is a demo/sample template for building your JMH benchmarks. Edit as needed.
        // Put your benchmark code here.

//        state.phaser.register();
        BlockingStudent BlockingStudent = new BlockingStudent("Student " + Thread.currentThread().getId(), (int) Thread.currentThread().getId(), state.bds);
        BlockingStudent.run();
//        int phase = state.phaser.getPhase();
//        int proc = Runtime.getRuntime().availableProcessors();
//        System.out.println("Current phase: " + phase);
//        state.forkJoinPool.submit(
//                new Thread() {
//                    final BlockingStudent blockingStudent = new BlockingStudent("Student " + currentThread().getId(), (int) currentThread().getId(), state.bds);
//
//                    public void run() {
////                        do {
//
//                            blockingStudent.run();
//
////                            state.phaser.arriveAndAwaitAdvance();
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
    @Group("Four")
    @GroupThreads(4)
    public void testBlocking4Threads(ThreadState state) {
        // This is a demo/sample template for building your JMH benchmarks. Edit as needed.
        // Put your benchmark code here.

//        state.phaser.register();
        //state.bds.setUpClasses();
//        int phase = state.phaser.getPhase();
     //   int proc = Runtime.getRuntime().availableProcessors();
//        System.out.println("Current phase: " + phase);
        BlockingStudent BlockingStudent = new BlockingStudent("Student " + Thread.currentThread().getId(), (int) Thread.currentThread().getId(), state.bds);
        BlockingStudent.run();
//        state.forkJoinPool.submit(
//                new Thread() {
//                    final BlockingStudent blockingStudent = new BlockingStudent("Student " + currentThread().getId(), (int) currentThread().getId(), state.bds);
//
//                    public void run() {
////                        do {
//
//                            blockingStudent.run();
//
////                            state.phaser.arriveAndAwaitAdvance();
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
    @Group("Eight")
    @GroupThreads(8)
    public void testBlocking8Threads(ThreadState state) {
        // This is a demo/sample template for building your JMH benchmarks. Edit as needed.
        // Put your benchmark code here.

//        state.phaser.register();
     //   state.bds.setUpClasses();
//        int phase = state.phaser.getPhase();
//        int proc = Runtime.getRuntime().availableProcessors();
//        System.out.println("Current phase: " + phase);
        BlockingStudent BlockingStudent = new BlockingStudent("Student " + Thread.currentThread().getId(), (int) Thread.currentThread().getId(), state.bds);
        BlockingStudent.run();
//        state.forkJoinPool.submit(
//                new Thread() {
//                    final BlockingStudent blockingStudent = new BlockingStudent("Student " + currentThread().getId(), (int) currentThread().getId(), state.bds);
//
//                    public void run() {
////                        do {
//
//                            blockingStudent.run();
//
////                            state.phaser.arriveAndAwaitAdvance();
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
    @Group("Sixteen")
    @GroupThreads(16)
    public void testBlocking16Threads(ThreadState state) {
        // This is a demo/sample template for building your JMH benchmarks. Edit as needed.
        // Put your benchmark code here.

//        state.phaser.register();
  //      state.bds.setUpClasses();
//        int phase = state.phaser.getPhase();
//        int proc = Runtime.getRuntime().availableProcessors();
//        System.out.println("Current phase: " + phase);
        BlockingStudent BlockingStudent = new BlockingStudent("Student " + Thread.currentThread().getId(), (int) Thread.currentThread().getId(), state.bds);
        BlockingStudent.run();
//        state.forkJoinPool.submit(
//                new Thread() {
//                    final BlockingStudent blockingStudent = new BlockingStudent("Student " + currentThread().getId(), (int) currentThread().getId(), state.bds);
//
//                    public void run() {
////                        do {
//
//                            blockingStudent.run();
//
////                            state.phaser.arriveAndAwaitAdvance();
////
////                        } while (!state.phaser.isTerminated());
//
//                    }
//
//                });
//        state.phaser.arriveAndDeregister();
    }

    @Benchmark
    @Fork(value = 2)
    @Measurement(iterations = 10, time = 1)
    @Warmup(iterations = 5, time = 1)
    @Group("ThirtyTwo")
    @GroupThreads(32)
    public void testBlocking32Threads(ThreadState state)  {
        BlockingStudent BlockingStudent = new BlockingStudent("Student " + Thread.currentThread().getId(), (int) Thread.currentThread().getId(), state.bds);
        BlockingStudent.run();
    }

//
//    public void testBlocking32Threads(ThreadState state) {
//        // This is a demo/sample template for building your JMH benchmarks. Edit as needed.
//        // Put your benchmark code here.
//
////        state.phaser.register();
//   //     state.bds.setUpClasses();
////        int phase = state.phaser.getPhase();
////        int proc = Runtime.getRuntime().availableProcessors();
////        System.out.println("Current phase: " + phase);
////        BlockingStudent BlockingStudent = new BlockingStudent("Student " + Thread.currentThread().getId(), (int) Thread.currentThread().getId(), state.bds);
////        BlockingStudent.run();
////        state.forkJoinPool.submit(
////                new Thread() {
////                    final BlockingStudent blockingStudent = new BlockingStudent("Student " + currentThread().getId(), (int) currentThread().getId(), state.bds);
////
////                    public void run() {
//////                        do {
////
////                            blockingStudent.run();
////
//////                            state.phaser.arriveAndAwaitAdvance();
////
//////                        } while (!state.phaser.isTerminated());
////
////                    }
////
////                });
////        state.phaser.arriveAndDeregister();
//    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(BlockingBenchmark.class.getSimpleName())
                .warmupIterations(5)
                .warmupTime(TimeValue.seconds(10))
                .measurementIterations(5)
                .forks(3)
                .result("bdsResults.json")
                .resultFormat(ResultFormatType.JSON)
                .build();
        new Runner(opt).run();

    }
}
