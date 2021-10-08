package uppgift1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class GymTest {

    Gym gdt = new Gym();
    Customer customer1 = new Customer(234321, "customer 1", "2021-10-07");
    Customer customer2 = new Customer(234322, "customer 2", "2020-10-07");
    Customer customer3 = new Customer(234323, "customer 3", "2019-10-07");

    @BeforeEach
    void setUp() {
        gdt.addCustomerIntoList(customer1);
        gdt.addCustomerIntoList(customer2);
        gdt.addCustomerIntoList(customer3);
    }

    @Test
    void generateCustomerListFromFile(){
        String fileName = "src/uppgift1/customers_test.txt";
        assertTrue(gdt.generateCustomerListFromFile(fileName).equals(gdt.getCustomerList()));
    }

    @Test
    void findCustomer(){
        // can be String, too
        long id = 234322;
        String name = "customer 2";
        long idNotExisting = 111111;
        String nameNotExisting = "customer 5";

        assertTrue(gdt.findCustomer(id) == customer2);
        assertTrue(gdt.findCustomer(name) == customer2);
        assertFalse(gdt.findCustomer(id) == customer1);
        assertFalse(gdt.findCustomer(name) == null);
        assertTrue(gdt.findCustomer(idNotExisting) == null);
        assertTrue(gdt.findCustomer(nameNotExisting) == null);
    }

    @Test
    void findCustomersLatestPayment(){
        long id = 234322;
        String name = "customer 2";
        long idNotExisting = 111111;
        String nameNotExisting = "customer 5";
        assertTrue(gdt.findCustomersLatestPayment(id) == "2020-10-07");
        assertTrue(gdt.findCustomersLatestPayment(name) == "2020-10-07");
        assertFalse(gdt.findCustomersLatestPayment(name) == "");
        assertFalse(gdt.findCustomersLatestPayment(name) == "2021-10-06");
        assertTrue(gdt.findCustomersLatestPayment(idNotExisting) == null);
        assertTrue(gdt.findCustomersLatestPayment(nameNotExisting) == null);
    }

    @Test
    void checkCustomersStatus(){
        LocalDate today = LocalDate.now();
        long id = 234321;
        String name = "customer 1";
        long idNotMember = 234323;
        String nameNotMember = "customer 3";
        long idNotExisting = 111111;
        String nameNotExisting = "customer 5";
        assertTrue(gdt.checkCustomersStatus(id, today).equals("customer 1 is a member.") );
        assertTrue(gdt.checkCustomersStatus(name, today).equals("customer 1 is a member.") );
        assertTrue(gdt.checkCustomersStatus(idNotMember, today).equals("customer 3's pass is not validate.") );
        assertTrue(gdt.checkCustomersStatus(nameNotMember, today).equals("customer 3's pass is not validate.") );
        assertTrue(gdt.checkCustomersStatus(idNotExisting, today).equals("111111 is not registered yet." ));
        assertTrue(gdt.checkCustomersStatus(nameNotExisting, today).equals("customer 5 is not registered yet." ));
    }

    @Test
    void createCustomerRecord(){
        String name = "customer 2";
        String nameNotExisting = "customer 5";
        assertTrue(gdt.createCustomerRecord(name) == true);
        assertTrue(gdt.createCustomerRecord(nameNotExisting) == false);
    }

    @Test
    void printCustomerRecord(){
        String name = "customer5";
        String nameNotExisting = "customer 5";
        assertTrue(gdt.printCustomerRecord(name) == true);
        assertTrue(gdt.printCustomerRecord(nameNotExisting) == false);
    }



}