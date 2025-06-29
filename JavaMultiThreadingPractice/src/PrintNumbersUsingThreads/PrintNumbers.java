package PrintNumbersUsingThreads;

public class PrintNumbers implements Runnable {

    @Override
    public void run() {
        try {
            for(int i = 0; i < 10; i++) {
                System.out.println("Current Number: " + i);
                Thread.sleep(500);
            }
        } catch (InterruptedException interruptedException) {
            System.out.println("Execption occurred during thread running");
            throw new RuntimeException(interruptedException);
        }
    }

    public static void main(String[] args) {
        PrintNumbers printNumbers = new PrintNumbers();
        Thread printNumbersThread = new Thread(printNumbers);
        printNumbersThread.start();
    }
}
