import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//source -> intermediate operation -> terminal operation
//       -> map/filter/sort        -> collect/forEach/reduce
public class JavaSteams {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(5, 1, 4, 2, 3);

        list.stream().map(x -> x * x).forEach(System.out::println);
        list.stream().map(x -> x * x).forEach(x -> System.out.println(x));
        List<Integer> updatedList = list.stream().map(x -> x * x).collect(Collectors.toList());
        System.out.println("Updated list: " + updatedList);
        System.out.println("Original list: " + list);

        list.stream().sorted().forEach(System.out::println);
        updatedList = list.stream().sorted().collect(Collectors.toList());
        System.out.println("Updated list: " + updatedList);
        System.out.println("Original list: " + list);

        list.stream().filter(x -> x % 2 == 0).forEach(System.out::println);
        updatedList = list.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
        System.out.println("Updated list: " + updatedList);
        System.out.println("Original list: " + list);

        int result = list.stream().filter(x -> x % 2 == 0).reduce(0, (a, b) -> a + b);
        System.out.println("Sum of even numbers: " + result);

    }
}
