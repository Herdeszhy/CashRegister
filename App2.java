import java.util.*;
import java.util.regex.*;

public class App {
    
    static Scanner input = new Scanner(System.in);
    
    static String[] usernames = new String[10];
    static String[] passwords = new String[10];
    static int userCount = 0;
    static boolean loggedIn = false;
    
    static final String USERNAME_PATTERN = "[a-zA-Z0-9]{3,10}"; // 3-10 alphanumeric characters
    static final String PASSWORD_PATTERN = "[a-zA-Z0-9]{4,10}"; // 4-10 alphanumeric characters
    
    public static void main(String[] args) {

        while (true) {
            if (!loggedIn) {
                showLoginMenu();
            } else {
                runCashRegister();
                loggedIn = false; 
            }
        }
    }
    
    public static void showLoginMenu() {
        System.out.println("\n===== LOGIN SYSTEM =====");
        System.out.println("1. Sign Up");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Choose option: ");
        
        try {
            int choice = input.nextInt();
            input.nextLine(); 
            
            switch (choice) {
                case 1: signUp(); break;
                case 2: login(); break;
                case 3: 
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option!");
            }
        } catch (Exception e) {
            System.out.println("Please enter a valid number");
            input.nextLine(); 
        }
    }
    
    public static void signUp() {
        if (userCount >= 10) {
            System.out.println("User limit reached!");
            return;
        }
        
        String username;
        do {
            System.out.print("Enter username (3-10 alphanumeric chars): ");
            username = input.nextLine();
            
            boolean exists = false;
            for (int i = 0; i < userCount; i++) {
                if (usernames[i] != null && usernames[i].equals(username)) {
                    System.out.println("Username already exists!");
                    exists = true;
                    break;
                }
            }
            if (exists) continue;
            
            if (!Pattern.matches(USERNAME_PATTERN, username)) {
                System.out.println("Invalid username format! Use 3-10 letters and numbers only.");
            } else {
                break; 
            }
        } while (true);
        
        String password;
        do {
            System.out.print("Enter password (4-10 alphanumeric chars): ");
            password = input.nextLine();
            
            if (!Pattern.matches(PASSWORD_PATTERN, password)) {
                System.out.println("Invalid password format! Use 4-10 letters and numbers only.");
            } else {
                break;
            }
        } while (true);
        
        usernames[userCount] = username;
        passwords[userCount] = password;
        userCount++;
        System.out.println("Account created successfully!");
    }
    
    public static void login() {
        System.out.print("Username: ");
        String username = input.nextLine();
        
        System.out.print("Password: ");
        String password = input.nextLine();
        
        for (int i = 0; i < userCount; i++) {
            if (usernames[i] != null && usernames[i].equals(username) && 
                passwords[i] != null && passwords[i].equals(password)) {
                System.out.println("Login successful!");
                loggedIn = true;
                return;
            }
        }
        
        System.out.println("Invalid username or password!");
    }
    
    public static void runCashRegister() {
        ArrayList<String> cart = new ArrayList<>();
        ArrayList<Integer> quantity = new ArrayList<>();
        ArrayList<Double> price = new ArrayList<>();
        boolean registrar = true;
        boolean newRegistrar = true;

        OUTER:
        while (registrar) {
            System.out.println("---------------------------------");
            System.out.println("---Welcome to my cash register---");
            System.out.println("---------------------------------");
            System.out.println("-------------Select--------------");
            System.out.println("         1. Add to cart          ");
            System.out.println("         2. View cart            ");
            System.out.println("         3. Buy now              ");
            System.out.println("         4. Logout               ");
            System.out.println("---------------------------------");
            System.out.print("        Enter number: ");
            
            try {
                int num = input.nextInt();
                input.nextLine();

                switch (num) {
                    case 1:
                        newRegistrar = true;
                        while (newRegistrar) {
                            System.out.println("\n----------- Your Cart -----------");
                            System.out.print("        Product name: ");
                            String name = input.nextLine();
                            cart.add(name);
                            
                            System.out.print("        Product quantity: ");
                            int quantities = input.nextInt();
                            quantity.add(quantities);
                            input.nextLine();
                            
                            System.out.print("        Product price: ");
                            double prices = input.nextDouble();
                            price.add(prices);
                            input.nextLine();
                            
                            while (true) { 
                                System.out.print("        Would you like to add? (y/n): ");
                                String add = input.nextLine();
                                
                                if (add.equalsIgnoreCase("n")) {
                                    newRegistrar = false;
                                    break;
                                } else if (add.equalsIgnoreCase("y")) {
                                    newRegistrar = true;
                                    break;
                                } else {
                                    System.out.println("        Invalid input. Please only answer in (y/n). ");
                                }   
                            }
                        }         
                        break;
                    case 2:
                        if (cart.isEmpty()) {
                            System.out.println("        Your cart is empty");
                        } else {
                            System.out.println("\n--------- Your Cart ---------");
                            for (int i = 0; i < cart.size(); i++) {
                                System.out.println(cart.get(i) + "        -Quantity: "+ quantity.get(i) + "       -price: "+ price.get(i));
                            }
                        }       
                        break;
                    case 3:
                        if (cart.isEmpty()) {
                            System.out.println("        Your cart is empty");
                        } else {
                            double total = 0;
                            System.out.println("\n--------- Final Receipt ---------");
                            for (int i = 0; i < cart.size(); i++) {
                                double itemTotal = quantity.get(i) * price.get(i);
                                total += itemTotal;
                                System.out.println(cart.get(i) + "      -Quantity: "+ quantity.get(i) + "     -price: "+ price.get(i) + " -total: "+ itemTotal);
                            }       
                            System.out.println("          Total Price: "+ total);
                            double money = 0;
                            do {
                                System.out.print("          Enter your money: ");
                                money = input.nextDouble();
                                
                                if (money < total) {
                                    System.out.println("          Insufficent funds! Please Try again!");
                                }
                            } while (money < total);
                            double change = money - total;
                            System.out.println("          Payment received: "+ money);
                            System.out.println("          Change: "+ change);
                            System.out.println("          Thank you for the purchase!!");
                            break OUTER;
                        }
                        break;
                    case 4:
                        System.out.println("        Logging out...");
                        return;
                    default:
                        System.out.println("---- Invalid input detected ----");
                        System.out.println("    Please only choose from 1-4");
                        break;
                }
            } catch (Exception e) {
                System.out.println("---- Invalid input detected ----");
                input.nextLine();
            }
        }
    }
}