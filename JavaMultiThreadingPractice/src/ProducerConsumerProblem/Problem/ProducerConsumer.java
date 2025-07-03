package ProducerConsumerProblem.Problem;

public class ProducerConsumer {
    private static final Object lockA = new Object();
    private static final Object lockB = new Object();

    private static int item = 0;
    private static boolean hasItem = false;


    public void produce() {
        while (true) {
            synchronized (lockA) {
                System.out.println("Producer acquired lockA");

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("producer waiting for lockB");
                synchronized (lockB) {
                    System.out.println("producer acquired lockB");
                    if(!hasItem) {
                        System.out.println("produced item " + item);
                        hasItem = true;
                    }
                    System.out.println("Producer release lockB");
                }
                System.out.println("producer release lockA");
            }

            try {Thread.sleep(100);}catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        }
    }

    public void consume() {
        while (true) {
            synchronized (lockB) {
                System.out.println("Consumer acquired lockB");

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("Consumer waiting for lockA");
                synchronized (lockA) {
                    System.out.println("Consumer acquired lockA");
                    if (hasItem) {
                        System.out.println("Consumed item " + item);
                        hasItem = false;
                    }
                    System.out.println("Consumer release lockA");
                }
                System.out.println("Consumer release lockB");
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        }
    }

    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer();
        Thread producer= new Thread(pc::produce, "producer");
        Thread consumer = new Thread(pc::consume, "consumer");

        producer.start();
        consumer.start();
    }
}
