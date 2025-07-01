package PrintNumbersAndCharactersAlternatively;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UsingReentrantLock {
    private Lock lock = new ReentrantLock();
    int number = 1;
    char c = 'a';
    private final Condition charCondition = lock.newCondition();
    private final Condition numberCondition = lock.newCondition();

    StringBuilder sb = new StringBuilder();
    boolean numberTurn = false;

    public void printCharacter() {
        lock.lock();
        try {
            while (c <= 'z') {
                while (numberTurn) charCondition.await();
                sb.append(c);
                c++;
                numberTurn = true;
                numberCondition.signal();
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        } finally {
            lock.unlock();
        }
    }

    public void printNumber() {
        lock.lock();
        try {
            while (number <= 26) {
                while (!numberTurn) numberCondition.await();
                sb.append(number);
                number++;
                numberTurn = false;
                charCondition.signal();
            }
        } catch (InterruptedException interruptedException) {
            System.out.println("Interrupted");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        UsingReentrantLock usingReentrantLock = new UsingReentrantLock();
        Thread numberPrinterThread = new Thread(usingReentrantLock::printNumber,
                "numberPrinterThread");

        Thread characterPrinterThread = new Thread(usingReentrantLock::printCharacter,
                "characterPrinterThread");

        numberPrinterThread.start();
        characterPrinterThread.start();

        try {
            numberPrinterThread.join();
            characterPrinterThread.join();
        } catch (InterruptedException e) {
            System.out.println("interrupted");
        }
        System.out.println(usingReentrantLock.sb.toString());
    }
}
