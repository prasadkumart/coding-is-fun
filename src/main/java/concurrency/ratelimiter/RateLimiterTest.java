package concurrency.ratelimiter;

public class RateLimiterTest {
    public static void main(String[] args) {
        int limit = 5; // 5 requests per minute
        RateLimiterService rateLimit = new RateLimiterService(limit);

        new RateLimiterHelper("User A", rateLimit).start();
        new RateLimiterHelper("User B", rateLimit).start();
    }
}
