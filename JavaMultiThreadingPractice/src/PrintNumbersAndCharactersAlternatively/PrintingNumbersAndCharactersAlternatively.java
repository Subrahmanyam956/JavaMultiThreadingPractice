package PrintNumbersAndCharactersAlternatively;

public class PrintingNumbersAndCharactersAlternatively {

    private final Object lock = new Object();
    int number = 1;
    char c = 'a';
    StringBuilder sb = new StringBuilder();
    boolean numberTurn = false;

    public void printCharacter() {
        synchronized (lock) {
            while('a' <= c  && c <= 'z') {
                try {
                    while(numberTurn) lock.wait();
                    sb.append(c);
                    c++;
                    numberTurn = true;
                    lock.notify();
                } catch (InterruptedException interruptedException) {
                    System.out.println("Interrupted");
                }
            }
        }
    }

    public void printNumber() {
        synchronized (lock) {
            while(number <= 26) {
                try {
                    while (!numberTurn) lock.wait();
                    sb.append(number);
                    number++;
                    numberTurn = false;
                    lock.notify();
                } catch (InterruptedException interruptedException) {
                    System.out.println("Interrupted");
                }
            }
        }
    }

    public static void main(String[] args) {
        PrintingNumbersAndCharactersAlternatively printingNumbersAndCharactersAlternatively
                = new PrintingNumbersAndCharactersAlternatively();

        Thread numberPrinterThread = new Thread(printingNumbersAndCharactersAlternatively::printNumber,
                "numberPrinterThread");

        Thread characterPrinterThread = new Thread(printingNumbersAndCharactersAlternatively::printCharacter,
                "characterPrinterThread");

        numberPrinterThread.start();
        characterPrinterThread.start();

        try {
            numberPrinterThread.join();
            characterPrinterThread.join();
        } catch (InterruptedException e) {
            System.out.println("interrupted");
        }
        System.out.println(printingNumbersAndCharactersAlternatively.sb.toString());
    }
}
