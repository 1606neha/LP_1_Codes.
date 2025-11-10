import java.util.Scanner;

public class ArithmeticJNI {
    public native double add(double a, double b);
    public native double subtract(double a, double b);
    public native double multiply(double a, double b);
    public native double divide(double a, double b);

    static {
        System.loadLibrary("ArithmeticDLL");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArithmeticJNI obj = new ArithmeticJNI();

        System.out.println("=== Arithmetic Operations using DLL (JNI) ===\n");

        System.out.print("Enter first number: ");
        double num1 = sc.nextDouble();

        System.out.print("Enter second number: ");
        double num2 = sc.nextDouble();

        System.out.println("\nResults:");
        System.out.println("Addition: " + obj.add(num1, num2));
        System.out.println("Subtraction: " + obj.subtract(num1, num2));
        System.out.println("Multiplication: " + obj.multiply(num1, num2));

        try {
            System.out.println("Division: " + obj.divide(num1, num2));
        } catch (Exception e) {
            System.out.println("Division Error: " + e.getMessage());
        }

        sc.close();
    }
}

