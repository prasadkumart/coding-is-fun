package concurrency;

import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SchedulerExecutorServiceExample {
    public static void main(String[] args) {
        System.out.println("Count down clock from 10 to 0...");

        //Scheduler object
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(11);

        System.out.println("Current Time: " + Calendar.getInstance().get(Calendar.SECOND));

        //schedule tasks
        //delay – the time from now to delay execution
        for(int i=10; i>=0; i--) {
            scheduler.schedule(new Task(i), 10-i, TimeUnit.SECONDS);
        }

        //shutdown the scheduler
        scheduler.shutdown();
    }
}

class Task extends Thread {
    private int counter;

    public Task(int counter) {
        this.counter = counter;
    }

    public void run() {
        System.out.println("Counter: " + counter + " Current Time: " + Calendar.getInstance().get(Calendar.SECOND));
    }
}