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
                if(menuNumber == 4 ){
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
        int customerStatus;
        LocalDate today = LocalDate.now();
        switch (menuNumber){
            case 1:
                System.out.println("Please enter customer's name or ID number.");
                if(scanner.hasNextLong()){
                    customer = gym.findCustomer(scanner.nextLong());
                } else if(scanner.hasNextLine()){
                    customer = gym.findCustomer(scanner.nextLine());
                } else {
                    System.out.println("Input should be name or ID number.");
                    break;
                }
                customerStatus = gym.checkCustomersStatus(customer, today);
                gym.printCustomersStatus(customer, customerStatus);
                break;

            case 2:
                System.out.println("Please enter customer's name that you would like to create a record for.");
                customerName = scanner.nextLine();
                customer = gym.findCustomer(customerName);
                customerStatus = gym.checkCustomersStatus(customer, today);
                gym.createCustomerRecord(customer, customerStatus);
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
