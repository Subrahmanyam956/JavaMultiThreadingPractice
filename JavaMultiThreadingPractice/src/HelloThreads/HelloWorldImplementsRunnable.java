package HelloThreads;

public class HelloWorldImplementsRunnable implements Runnable {

    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            System.out.println("Hello CC");
        }
    }


    public static void referencingTheMethodToRun() {
        for(int i = 0; i < 5; i++) {
            System.out.println("Method Referencing Example");
        }
    }

    public static void main(String[] args) {
        HelloWorldImplementsRunnable helloWorldImplementsRunnable = new HelloWorldImplementsRunnable();
        Thread t1 = new Thread(helloWorldImplementsRunnable);
        t1.start();

        Thread t2 = new Thread(() -> System.out.println("Lamba Expression Thread"));
        t2.start();

        Thread t3 = new Thread(HelloWorldImplementsRunnable::referencingTheMethodToRun);
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException interruptedException) {
            System.out.println("Exception");
        }

    }
}
