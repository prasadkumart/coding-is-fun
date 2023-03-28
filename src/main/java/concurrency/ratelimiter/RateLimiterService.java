package concurrency.ratelimiter;

import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimiterService {
    int ratelimit;
    Map<String, LinkedList<Request>> userRequestMap;

    public RateLimiterService(int ratelimit) {
        this.ratelimit = ratelimit;
        this.userRequestMap = new ConcurrentHashMap<>();
    }

    public synchronized boolean hit(String username, Instant timestamp) {
        //new user
        if (!userRequestMap.containsKey(username)) {
            return addNewUser(username);
        } else {
            int requestsCount = getTotalElpasedRequests(username);
            //System.out.println("requestsCount " + requestsCount);
            if (requestsCount < ratelimit) {
                return updateExistingUser(username, timestamp);
            } else {
                boolean actionTaken = false;
                //remove elapsed requests from the linked list
                LinkedList<Request> requests = userRequestMap.get(username);
                for(int i=0; i<requests.size(); i++) {
                    Duration duration = Duration.between(requests.get(i).getTimestamp(), timestamp);

                    //elapsed time is more than a minute
                    if (duration.getSeconds() >= 60) {
                        userRequestMap.get(username).remove(i);
                        actionTaken = true;
                    } else {
                        break;
                    }
                }

                if (actionTaken) {
                    return updateExistingUser(username, timestamp);
                }

                return false;
            }
        }
    }

    private boolean addNewUser(String username) {
        LinkedList<Request> requests = new LinkedList<>();
        requests.add(new Request(1, Instant.now()));
        userRequestMap.put(username, requests);

        System.out.println("New User Added!!!" + username);

        return true;
    }

    private boolean updateExistingUser(String username, Instant timestamp) {
        LinkedList<Request> requests = userRequestMap.get(username);
        requests.add(new Request(1, timestamp));
        userRequestMap.put(username, requests);

        //System.out.println("Updated User requests: " + username);

        return true;
    }

    public Integer getTotalElpasedRequests(String username) {
        //return userRequestMap.get(user).stream().mapToInt(Request::getCount).sum();
        return userRequestMap.get(username)
                .stream()
                .mapToInt(Request::getCount)
                .sum();
    }
}
