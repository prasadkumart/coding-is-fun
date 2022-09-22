package map.reduce;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EmployeeDataGen {
    public static List<Employee> generateData(int number) {
        List<Employee> employees = new ArrayList<>();

        for (int i=0; i<number/2; i++) {
            employees.add(new Employee(i, "Name"+i, "A", new Random().nextInt(1000)));
        }
        for (int i=0; i<number/2; i++) {
            employees.add(new Employee(i, "Name"+i, "B", new Random().nextInt(1000)));
        }

        return employees;
    }
}
