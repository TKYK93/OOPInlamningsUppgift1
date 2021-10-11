package uppgift1;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Gym gym = new Gym();

    public static void main(String[] args) {
	    gym.setCustomerList(gym.generateCustomerListFromFile("src/uppgift1/customers.txt"));
        while (true){
            printMenu();
            if(scanner.hasNextInt()){
                int menuNumber = scanner.nextInt();
                scanner.nextLine();
                if(menuNumber ==4 ){
                    break;
                }
                selectMenu(menuNumber);
            } else {
                scanner.nextLine();
                System.out.println("You need to choose a number on the menu.");
            }
        }
    }

    public static void printMenu(){
        System.out.println("=================Menu==================");
        System.out.println("1 : Check customer's status.");
        System.out.println("2 : Create customer's record for today.");
        System.out.println("3 : Show customer's record.");
        System.out.println("4 : Exit.");
        System.out.println("========================================");
    }

    public static void selectMenu(int menuNumber) {
        String customerName;
        Customer customer;
        switch (menuNumber){
            case 1:
                LocalDate today = LocalDate.now();
                System.out.println("Please enter customer's name or ID number.");
                if(scanner.hasNextLong()){
                    customer = gym.findCustomer(scanner.nextLong());
                    gym.printCustomersStatus(customer,today);
                } else if(scanner.hasNextLine()){
                    customer = gym.findCustomer(scanner.nextLine());
                    gym.printCustomersStatus(customer, today);
                } else {
                    System.out.println("Input should be name or ID number.");
                }
                break;

            case 2:
                System.out.println("Please enter customer's name that you would like to create a record for.");
                customerName = scanner.nextLine();
                customer = gym.findCustomer(customerName);
                gym.createCustomerRecord(customer, LocalDate.now());
                break;

            case 3:
                System.out.println("Please enter customer's name that you would like to print a record of.");
                customerName = scanner.nextLine();
                gym.printCustomerRecord(customerName);
                break;

            default:
                System.out.println("You need to choose a number on the menu.");
                break;
        }
    }
}
