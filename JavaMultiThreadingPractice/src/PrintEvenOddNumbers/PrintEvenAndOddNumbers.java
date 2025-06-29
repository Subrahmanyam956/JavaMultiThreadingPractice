package PrintEvenOddNumbers;

public class PrintEvenAndOddNumbers {

    private int targetVal;
    private int curNum = 1;

    public PrintEvenAndOddNumbers(int targetVal) {
        this.targetVal = targetVal;
    }

    public synchronized void oddNumberPrinterThread() {

        while(curNum < targetVal) {
            try {
                while (curNum % 2 == 0) {
                    wait();
                }

                if(curNum % 2 == 1) {
                    System.out.println(Thread.currentThread().getName() + " printing val : " + curNum);
                    curNum += 1;
                    notify();
                }

            } catch (InterruptedException interruptedException) {
                System.out.println("Exception occurred during printing the number");
            }
        }
    }

    public synchronized void evenNumberPrinterThread() {
        while (curNum < targetVal) {
            try {
                while (curNum % 2 == 1) {
                    wait();
                }

                if(curNum % 2 == 0) {
                    System.out.println(Thread.currentThread().getName() + " printing val : " + curNum);
                    curNum += 1;
                    notify();
                }

            } catch (InterruptedException interruptedException) {
                System.out.println("Exception occurred during printing the number");
            }
        }
    }

    public static void main(String[] args) {
        PrintEvenAndOddNumbers printEvenAndOddNumbers = new PrintEvenAndOddNumbers(50);

        Thread evenThread = new Thread(printEvenAndOddNumbers::evenNumberPrinterThread, "evenThread");
        Thread oddThread = new Thread(printEvenAndOddNumbers::oddNumberPrinterThread, "oddThread");
        evenThread.start();
        oddThread.start();
    }
}
