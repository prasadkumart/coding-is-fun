package concurrency.ratelimiter;

import java.time.Instant;

public class RateLimiterHelper extends Thread {
    RateLimiterService rateLimiterService;

    public RateLimiterHelper(String user, RateLimiterService rateLimiterService) {
        super(user);
        this.rateLimiterService = rateLimiterService;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 20; i++) {
            System.out.println("Thread Name - " + getName() + ", Time - " + i + ", rate limit: " + hit(getName(), Instant.now()));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("DONE! " + getName());
    }

    public boolean hit(String user, Instant ts) {
        return rateLimiterService.hit(user, ts);
    }
}
