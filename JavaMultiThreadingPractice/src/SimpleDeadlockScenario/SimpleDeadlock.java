package SimpleDeadlockScenario;

public class SimpleDeadlock {

    private static final Object lockObjectA = new Object();
    private static final Object lockObjectB = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (lockObjectA) {
                System.out.println("Inside synchronized A Thread T1");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("interrupted");
                }
                System.out.println("T1 waiting for lockObjectB");
                synchronized (lockObjectB) {
                    System.out.println("inside synchronized B Thread T1");
                }
            }

        });

        Thread t2 = new Thread(() -> {
            synchronized (lockObjectB) {
                System.out.println("Inside synchronized B Thread T2");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("interrupted");
                }
                System.out.println("T2 waiting for lockObjectA");
                synchronized (lockObjectA) {
                    System.out.println("inside synchronized A Thread T2");
                }
            }

        });

        t1.start();
        t2.start();

        System.out.println("execution completed");
    }
}
