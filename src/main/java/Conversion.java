import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Conversion {
    public static void main(String[] args) {
        //ArrayList to array of String
        List<String> list = new ArrayList<>();
        list.add("first");
        list.add("second");
        list.add("third");

        //list to array
        String[] arr = list.toArray(String[]::new);
        //String[] arr = list.toArray(new String[list.size()]);
        //Arrays.stream(arr).forEach(x -> System.out.println(x));
        Arrays.stream(arr).forEach(System.out::println);

        //using java8
        List<String> list1 = Arrays.asList("apple","banana", "kiwi");
        //Using Streams with method reference
        String[] strArray = list1.stream().toArray(String[]::new);
        Arrays.stream(strArray).forEach(x -> System.out.println(x));

        //using java8
        List<String> list2 = Arrays.asList("broccoli","zucchini", "peppers");
        // Using Streams with lambda expression
        String[] strArray1 = list1.stream().toArray(s -> new String[s]);
        Arrays.stream(strArray1).forEach(x -> System.out.println(x));

        String[] strArr = {"A","B","C"};
        List<String> arrList = Arrays.asList(strArr);
        System.out.println(arrList);


    }
}