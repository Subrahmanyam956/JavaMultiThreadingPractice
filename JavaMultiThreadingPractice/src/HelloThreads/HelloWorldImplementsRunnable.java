package HelloThreads;

public class HelloWorldImplementsRunnable implements Runnable {

    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            System.out.println("Hello CC");
        }
    }

    public static void main(String[] args) {
        HelloWorldImplementsRunnable helloWorldImplementsRunnable = new HelloWorldImplementsRunnable();
        Thread t1 = new Thread(helloWorldImplementsRunnable);
        t1.start();
    }
}
