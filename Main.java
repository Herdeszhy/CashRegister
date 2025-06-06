import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        
        System.out.println("-------------------");
        System.out.println(" Simple Calculator");
        System.out.println("-------------------");
        
        System.out.print("Enter first number: ");
        int num1 = input.nextInt();
        
        System.out.print("Enter second number: ");
        int num2 = input.nextInt();
        
        int sumAdd = num1 + num2;
        int sumDiff = num1 - num2;
        int sumMulti = num1 * num2;
        double sumDiv = num1 / num2;
        int sumMod = num1 % num2;
        
        System.out.println("-------------------");
        System.out.println("Addition: "+ sumAdd);
        System.out.println("Subtraction: "+ sumDiff);
        System.out.println("Multiplication: "+ sumMulti);
        System.out.println("Division: "+ sumDiv);
        System.out.println("Modulo: "+ sumMod);
        

        
        
    }
}