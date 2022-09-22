package map.reduce;

public class Employee {
    int id;
    String name;
    String lcat;
    int salary;

    public Employee(int id, String name, String lcat, int salary) {
        this.id = id;
        this.name = name;
        this.lcat = lcat;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLcat() {
        return lcat;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lcat='" + lcat + '\'' +
                ", salary=" + salary +
                '}';
    }
}
