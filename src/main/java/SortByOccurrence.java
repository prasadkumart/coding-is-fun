import java.util.*;

public class SortByOccurrence {
    Set<Pair> set;
    public SortByOccurrence() {
        set = new TreeSet<>();
    }
    class Pair implements Comparable<Pair>{
        String keyword;
        int occurrence;
        public Pair(String keyword, int occurrence) {
            this.keyword = keyword;
            this.occurrence = occurrence;
        }

        public String getKeyword() {
            return keyword;
        }

        public int getOccurrence() {
            return occurrence;
        }

        public void setOccurrence(int occurrence) {
            this.occurrence = occurrence;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.occurrence == o.occurrence) { //O(1)
                return this.keyword.compareTo(o.keyword); //O(L)
            }
            return Integer.compare(this.occurrence, o.occurrence);
        }
    }

    // tree set TC to add an object: time complexity to add a node into a tree set (which uses red-black tree) * complexity of the comparator
    // O(log N) N is no of words * O(L) L is the length of the word
    //TC: O(L * logN)
    public void add(String keyword) {
        Pair existing = set.stream().filter(x -> x.getKeyword().equals(keyword)).findFirst().orElse(null); //O(N)
        if (null == existing) {
            set.add(new Pair(keyword, 1)); // O(1)
        } else {
            // tree set TC to remove an object: time complexity to add a node into a tree set (which uses red-black tree) * complexity of the comparator
            // O(log N) N is no of words * O(L) L is the length of the word
            //TC: O(L * logN)
            set.remove(existing);
            existing.setOccurrence(existing.getOccurrence() + 1);
            set.add(existing);
        }
    }

    public void tail(int k) {
        Iterator<Pair> iterator = set.iterator();
        for (int i=0; i<k; i++) {
            if (iterator.hasNext()) {
                Pair pair = iterator.next();
                System.out.println(pair.getKeyword() + " " +pair.getOccurrence());
            }
        }
    }

    public static void main(String[] args) {
        TreeMap<Integer, String> map = new TreeMap<>();
        SortByOccurrence SortByOccurrence = new SortByOccurrence();
        String[] keywords = {"apple","iPhone", "nike", "iPhone",  "iPad", "adidas", "babolat", "nike", "apple"};
        for(String keyword: keywords) {
            SortByOccurrence.add(keyword);
            //map.put(keyword, map.getOrDefault(keyword, 0) +1);
        }
        //N long N
        // N is no of keywords
        // M max length of the keyword

        //iphone, 1
        //[apple, 1]
        //adidas, 1
        //nike, 1≠≠≠≠
        //iPad, 1


        SortByOccurrence.tail(4);
        System.out.println("-------");
        SortByOccurrence.add("bebo");
        SortByOccurrence.tail(2);
    }
}
