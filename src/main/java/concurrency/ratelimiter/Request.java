package concurrency.ratelimiter;

import java.time.Instant;

public class Request {
    private int count;
    private Instant timestamp;

    public Request(int count, Instant timestamp) {
        this.count = count;
        this.timestamp = timestamp;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
