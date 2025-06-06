import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.*;

public class test {
    
        static Scanner input = new Scanner(System.in);
        static String [] usernames = new String[10];
        static String [] passwords = new String[10];
        static int userCount = 0;
        static boolean loggedIn = false;
        static String currentUserName = null;

        static final String USERNAME_PATTERN = "^[a-zA-Z0-9]{3,10}$";
        static final String PASSWORD_PATTERN = "^[a-zA-Z0-9]{4,10}$";
        
        public static void main(String[]args) {

            usernames[0] = "admin";
            passwords[0] = "pass123";
            userCount = 1;

            while (true) {
                if (!loggedIn) {
                    showLoginMenu();
                } else {
                    runCashRegister(currentUserName);
                    loggedIn = false;
                }
            }
        }

        public static void showLoginMenu() {
            System.out.println("---------------------------------");
            System.out.println("---        LOGIN SYSTEM       ---");
            System.out.println("1.          Sign up");
            System.out.println("2.          Login");
            System.out.println("3.          Exit");
            System.out.println("---------------------------------");
            System.out.print("           Choose option: ");

            try {
                int choice = input.nextInt();

                switch(choice) {
                    case 1: 
                        signUp(); 
                        break;
                    case 2: 
                        Login(); 
                        break;
                    case 3:
                        System.out.println("Goodbye!!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid option");
                }

            } catch (Exception e) {
                System.out.println("Please enter a valid number");
                input.nextLine();
            }
        }

        public static void signUp() {
            if (userCount >= 10 ) {
                System.out.println("User limit reached!");
                return;
            }

            input.nextLine();
            String username;
            do {
                System.out.print("Enter username (3-10 alphanumeric chars): ");
                username = input.nextLine().trim();

                Boolean exists = false;
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

            public static void Login() {
                input.nextLine();
                
                System.out.print("Username: ");
                String username = input.nextLine();

                System.out.print("Password: ");
                String password = input.nextLine();

                for (int i = 0; i < userCount; i++) {
                    if (usernames[i] != null && usernames[i].equals(username) && 
                    passwords[i] != null && passwords[i].equals(password)) {
                        System.out.println("Login successful!");
                        loggedIn = true;
                        currentUserName = username;
                        return;
                    }
                }

                System.out.println("Invalid username or password.");
            }

        public static void runCashRegister(String currentUserName) {

        ArrayList<String> cart = new ArrayList<>();
        ArrayList<Integer> quantity = new ArrayList<>();
        ArrayList<Double> price = new ArrayList<>();
        boolean registrar = true;
        boolean newRegistrar = true;

            OUTER:
            while (registrar) {
                System.out.println("---------------------------------");
                System.out.println("Hello "+ currentUserName +"!");
                System.out.println("Welcome to the Cash register.");
                System.out.println("---------------------------------");
                System.out.println("I'm Harold, your cashier for today.");
                System.out.println("---------------------------------");
                System.out.println("---------------------------------");
                System.out.println("---       Cash Register       ---");
                System.out.println("---------------------------------");
                System.out.println("---          Select           ---");
                System.out.println("         1. Add to cart          ");
                System.out.println("         2. View cart            ");
                System.out.println("         3. Remove item          ");
                System.out.println("         4. Update item          ");
                System.out.println("         5. Buy now              ");
                System.out.println("---------------------------------");
                System.out.print("        Enter number: ");
                int num = input.nextInt();
                input.nextLine();

                switch (num) {
                    case 1:
                        while (newRegistrar) {
                            System.out.println("----------- Your Cart -----------");
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

                        }         break;
                    case 2:
                        if (cart.isEmpty()) {
                            System.out.println("        Your cart is empty");
                        } else {
                            System.out.println("--------- Your Cart ---------");
                            for (int i = 0; i < cart.size(); i++) {
                                System.out.println(cart.get(i) + "        - Quantity: "+ quantity.get(i) + "        -price: "+ price.get(i));
                            }
                        }       break;
                    case 3:
                        if (cart.isEmpty()) {
                            System.out.println("        Your cart is empty");
                        } else {
                            System.out.println("--------- Items in Cart ---------");
                        for (int i = 0; i < cart.size(); i++) {
                            System.out.println((i + 1) + ". " + cart.get(i));
                        }

                        System.out.print("Enter the name of the item to remove: ");
                        String itemToRemove = input.nextLine();

                        int index = cart.indexOf(itemToRemove);
                            if (index != -1) {
                                cart.remove(index);
                                quantity.remove(index);
                                price.remove(index);
                                System.out.println("        Item removed.");
                            } else {
                            System.out.println("        Item not found in the cart.");
                            }

                            registrar = true;
                            
                        }       break;
                    case 4: 
                        if (cart.isEmpty()) {
                            System.out.println("        Your cart is empty");
                        } else {
                            System.out.println("--------- Items in Cart ---------");
                        for (int i = 0; i < cart.size(); i++) {
                            System.out.println((i + 1) + ". " + cart.get(i));
                        }

                        System.out.print("Enter the name of the item to update: ");
                        String itemToUpdate = input.nextLine();

                        int index = cart.indexOf(itemToUpdate);
                            if (index != -1 ) {
                                System.out.print("Enter new name (or press Enter to keep \"" + cart.get(index) + "\"): ");
                                String newName= input.nextLine();
                                if (!newName.isEmpty()) {
                                    cart.set(index, newName);
                                }

                            System.out.print("Enter new quantity (current: " + quantity.get(index) + "): ");
                            int newQty = input.nextInt();
                            quantity.set(index, newQty);
                            input.nextLine();

                            System.out.print("Enter new price (current: " + price.get(index) + "): ");
                            Double newPrice = input.nextDouble();
                            price.set(index, newPrice);
                            input.nextLine();

                            System.out.println("Item updated successfully.");

                            } else {
                                System.out.println("Item not found in the cart.");
                            }

                            registrar = true;

                        }       break;
                    case 5:
                        if (cart.isEmpty()) {
                            System.out.println("        Your cart is empty");

                        } else {
                            double total = 0;
                            StringBuilder receipt = new StringBuilder();
                            System.out.println("\n--------- Final Receipt ---------");
                            System.out.println("Cashier: Harold");
                            receipt.append("Cashier: ").append("Harold").append("\n");
                            System.out.println("Customer: "+ currentUserName);
                            receipt.append("Customer: ").append(currentUserName).append("\n");
                            
                            for (int i = 0; i < cart.size(); i++) {
                                double itemTotal = quantity.get(i) * price.get(i);
                                total += itemTotal;
                                System.out.println(cart.get(i) + "      -Quantity: "+ quantity.get(i) + "     -price: "+ price.get(i) + " -total: "+ itemTotal);
                                
                                receipt.append(cart.get(i))
                                .append(" | Qty: ").append(quantity.get(i))
                                .append(" | Price: ").append(price.get(i))
                                .append(" | Total: ").append(itemTotal).append("\n");
                                
                            }       
                            
                            System.out.println("          Total Price: "+ total);
                            receipt.append("Total : ").append(total).append("\n");

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
                            System.out.println("        Thank you for the purchase!!");

                            receipt.append("Payment: ").append(money).append("\n");
                            receipt.append("Change: ").append(change).append("\n");

                            try (BufferedWriter writer = new BufferedWriter(new FileWriter("Transaction.txt", true))) {
                                writer.write("--------------  Transaction Receipt  -------------\n");
                                LocalDateTime now = LocalDateTime.now();
                                DateTimeFormatter real = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                String formattedDateTime = now.format(real); 
                                writer.write("Date and Time: "+ formattedDateTime);
                                writer.newLine();
                                writer.write(receipt.toString());
                                writer.write("--------------------------------------------------\n");
                                System.out.println("        Transaction saved to file.");

                            } catch (IOException e) {
                                System.out.print("Error writing transaction to file");
                            }

                            System.exit(0);
                            
                        }
                        break;
                        
                    default:
                        System.out.println("---- Invalid input detected ----");
                        System.out.println("    Please only choose from 1-3");
                        break;
                }
            }
        }
}