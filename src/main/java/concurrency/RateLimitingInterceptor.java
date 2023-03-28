package concurrency;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimitingInterceptor { //extends HandlerInterceptorAdapter {
    private Map<String, Optional<SimpleRateLimiter>> limiters = new ConcurrentHashMap<>();

    private Optional<SimpleRateLimiter> getRateLimiter(String clientId) {
        if (limiters.containsKey(clientId)) {
            return limiters.get(clientId);
        } else {
            synchronized(clientId.intern()) {
                // double-checked locking to avoid multiple-reinitializations
                if (limiters.containsKey(clientId)) {
                    return limiters.get(clientId);
                }

                //SimpleRateLimiter rateLimiter = createRateLimiter(clientId);

                //limiters.put(clientId, rateLimiter);
                //return rateLimiter;
            }
        }

        return null;
    }
}
