package com.wormwood;

import com.netflix.loadbalancer.reactive.ServerOperation;

import java.util.concurrent.CountDownLatch;

/**
 * Created by kasimodo on 2017-07-14.
 */
public class ThreadTest {
    public static void main(String ag[]) throws InterruptedException {
        int N=5;
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(N);

        for (int i = 0; i < N; ++i) // create and start threads
            new Thread(new Worker(startSignal, doneSignal,i)).start();

        doSomethingElse("work1");            // don't let run yet
        startSignal.countDown();      // let all threads proceed
        doSomethingElse("work2");
        doneSignal.await();           // wait for all to finish
        System.out.println("all done");
    }

    static class Worker implements Runnable {
        private final CountDownLatch startSignal;
        private final CountDownLatch doneSignal;
        private int workerId;

        Worker(CountDownLatch startSignal, CountDownLatch doneSignal,int workerId) {
            this.startSignal = startSignal;
            this.doneSignal = doneSignal;
            this.workerId=workerId;
        }

        public void run() {
            try {
                startSignal.await();
                doWork(workerId);
                doneSignal.countDown();
            } catch (InterruptedException ex) {
            } // return;
        }

        void doWork(int aa) {
            System.out.println("work is "+aa);
        }


    }


    static void doSomethingElse(String aa) {
        System.out.println(aa);
    }
}
