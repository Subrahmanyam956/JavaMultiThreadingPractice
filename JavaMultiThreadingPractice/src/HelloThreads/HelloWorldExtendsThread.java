package HelloThreads;

public class HelloWorldExtendsThread extends Thread {

    public void run() {
        for(int i = 0; i < 10; i++) {
            System.out.println("Hello CC");
        }
    }

    public static void main(String[] args) {
        HelloWorldExtendsThread helloWorldExtendsThread = new HelloWorldExtendsThread();
        helloWorldExtendsThread.start();
    }
}
