package concurrency.oddeven;


public class EvenOddNumbers {
    static int N;
    int number = 1;

    private void printEvenNumber() {
        synchronized (this) {
            while(number<N) {
                if (number%2 == 0) {
                    try {
                        //calling wait() forces the current thread to wait until some other
                        // thread invokes notify() or notifyAll() on the same object.
                        // For this, the current thread must own the object's monitor.
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println(number++);

                //notify other thread
                notify();
            }
        }
    }

    private void printOddNumber() {
        synchronized (this) {
            while(number<N) {
                if (number%2 == 1) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println(number++);

                //notify other thread
                notify();
            }
        }
    }

    public static void main(String[] args) {
        N = 100;

        EvenOddNumbers evenOddNumbers = new EvenOddNumbers();

        //even thread
        Thread evenNoThread = new Thread(new Runnable() {
            @Override
            public void run() {
                evenOddNumbers.printEvenNumber();
            }
        });

        Thread oddNoThread = new Thread(new Runnable() {
            @Override
            public void run() {
                evenOddNumbers.printOddNumber();
            }
        });

        //start the threads
        evenNoThread.start();
        oddNoThread.start();
    }
}
