public class JavaLambdas {
    public static void main(String[] args) {
        //lambda expression
        MyLambda lambda = () -> System.out.println("Hello");
        lambda.display();

        //lambda interface
        ArthExp add = (a, b) -> a +b;
        System.out.println("Sum :" + add.operation(4,5));

        ArthExp multiply = (a, b) -> a * b;
        System.out.println("multiply :" + multiply.operation(4,5));
    }
}

//functional interface
interface MyLambda {
    void display();
}

interface ArthExp {
    int operation(int a, int b);
}
