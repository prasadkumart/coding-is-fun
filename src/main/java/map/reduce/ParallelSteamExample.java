package map.reduce;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParallelSteamExample {
    public static void main(String[] args) {
//        long start = 0, end = 0;
//        start = System.currentTimeMillis();
//        IntStream.range(1,100).forEach(System.out::println);
//        end = System.currentTimeMillis();
//        System.out.print("Plain steam execution time: " + (end-start));
//
//        start = System.currentTimeMillis();
//        IntStream.range(1,100).parallel().forEach(System.out::println);
//        end = System.currentTimeMillis();
//        System.out.print("Parallel steam execution time: " + (end-start));

        //IntStream.range(1,10).forEach(x -> System.out.println("Thread: " + Thread.currentThread().getName()));
        //IntStream.range(1,10).parallel().forEach(x -> System.out.println("Thread: " + Thread.currentThread().getName()));

        int availableCores = Runtime.getRuntime().availableProcessors();
        System.out.println("Available Processors/CPU Cores: " + availableCores);

        List<Employee> employeeList = EmployeeDataGen.generateData(4);

        Map<String, List<Employee>> map = employeeList.stream()
                .collect(Collectors.groupingBy(e -> e.getLcat()));
        System.out.println(map);

        Map<String, Employee> highMap = employeeList.stream()
                .collect(
                        Collectors.groupingBy(e -> e.getLcat(),
                                Collectors.collectingAndThen(
                                        Collectors.maxBy(Comparator.comparingInt(e -> e.getSalary())),
                                        Optional::get
                                ))
                );
        System.out.println(highMap);


        long start = 0, end = 0;
        start = System.currentTimeMillis();
        double avg = employeeList.stream()
                .map(Employee::getSalary)
                .mapToDouble(e -> e).average().getAsDouble();
        end = System.currentTimeMillis();
        System.out.println("avg : " + avg);
        System.out.println("Plain steam execution time: " + (end-start));

        start = System.currentTimeMillis();
        avg = employeeList.parallelStream()
                .map(Employee::getSalary)
                .mapToDouble(e -> e).average().getAsDouble();
        end = System.currentTimeMillis();
        System.out.println("Parallel steam execution time: " + (end-start));


    }
}
