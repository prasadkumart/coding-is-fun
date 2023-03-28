package concurrency;

import java.util.concurrent.Semaphore;

//https://www.geeksforgeeks.org/semaphore-in-java/
//A Shared resouce
class Shared {
    static int counter = 0;
}

//Thread class
class MyThread extends Thread {
    Semaphore semaphore;
    String threadName;

    public MyThread(Semaphore semaphore, String threadName) {
        super(threadName);
        this.semaphore = semaphore;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        //Thread A will increment counter
        if (threadName.equalsIgnoreCase("A")) {
            try {
                //get the permit to access semaphore resource (counter)
                System.out.println("Starting " + threadName);

                //acquire the lock
                semaphore.acquire();
                System.out.println(threadName + " gets a lock");

                //access the sharing resource
                //other waiting threads will continue waiting until lock is released
                for(int i=0; i<5; i++) {
                    Shared.counter++;

                    System.out.println(threadName + ": " + Shared.counter);

                    //make thread sleep (mills)
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            //release the permit
            System.out.println(threadName + " release the permit");
            semaphore.release();

        } else {
            //Thread B will decrement counter

            try {
                //get the permit to access semaphore resource (counter)
                System.out.println("Starting " + threadName);

                //acquire the lock
                semaphore.acquire();
                System.out.println(threadName + " gets a lock");

                //access the sharing resource
                //other waiting threads will continue waiting until lock is released
                for(int i=0; i<5; i++) {
                    Shared.counter--;

                    System.out.println(threadName + ": " + Shared.counter);

                    //make thread sleep (mills)
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            //release the permit
            System.out.println(threadName + " release the permit");
            semaphore.release();
        }
    }
}

public class SemaphoreDemo {
    public static void main(String[] args) throws InterruptedException {
        // creating a Semaphore object with number of permits 1
        Semaphore semaphore = new Semaphore(1);

        MyThread threadA = new MyThread(semaphore, "A");
        MyThread threadB = new MyThread(semaphore, "B");

        //starting thread A &B
        threadA.start();
        threadB.start();

        //waiting for threads A and B, otherwise below SOP will be executed
        threadA.join();
        threadB.join();

        // count will always remain 0 after
        // both threads will complete their execution
        System.out.println("Main... count: " + Shared.counter);
    }
}
