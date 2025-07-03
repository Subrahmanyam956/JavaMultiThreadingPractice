package ProducerConsumerProblem.Problem;

import java.util.LinkedList;
import java.util.Queue;

class BrokenBufferImplementation {
    int capacity  = 1;
    Queue<Integer> queue = new LinkedList<>();

    public synchronized void produce(int val) {
        while(queue.size() == capacity) {
            try {
                System.out.println("Producer waiting");
                wait();
            } catch (InterruptedException interruptedException) {
                System.out.println("exception");
            }
        }
        queue.add(val);
        System.out.println("Produced value : " + val);
        notify();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void consume() {
        while(queue.isEmpty()) {
            try {
                System.out.println("Consumer waiting");
                wait();
            } catch (InterruptedException interruptedException) {
                System.out.println("exception");
            }
        }

        System.out.println("consumed value : " + queue.poll());
        notify();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}

public class BrokenBuffer {

    public static void main(String[] args) {
        BrokenBufferImplementation bbi = new BrokenBufferImplementation();

        Thread pThread = new Thread(() -> {
            int val = 0;
            while(true) {
                bbi.produce(val++);
            }
        });

        Thread cThread = new Thread(() -> {
            while(true) {
                bbi.consume();
            }
        }) ;

        pThread.start();
        cThread.start();
    }

}
