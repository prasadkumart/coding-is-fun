import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//source -> intermediate operation -> terminal operation
//       -> map/filter/sort        -> collect/forEach/reduce
// map(Function<? super T,​? extends R> mapper) : returns a stream of output
// map can use predefined functions such as mapToInt, mapToDouble, or custom
// list.stream().mapToInt(Integer::intValue).toArray() - to convert Integer ArrayList to int array
// list.stream().map(i -> i*i).toArray() - square list elements and convert it to int array
// map
public class JavaSteams {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(5, 1, 4, 2, 3);

        list.stream().map(x -> x * x).forEach(x -> System.out.println(x));
        list.stream().map(x -> x * x).forEach(System.out::println);
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
