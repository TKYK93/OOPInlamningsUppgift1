package uppgift1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class GymTest {

    Gym gdt = new Gym();
    Customer customer1 = new Customer(234321, "customer 1", "2021-10-07");
    Customer customer2 = new Customer(234322, "customer 2", "2020-10-07");
    Customer customer3 = new Customer(234323, "customer 3", "2019-10-07");

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


    @BeforeEach
    void setUp() {
        gdt.addCustomerIntoList(customer1);
        gdt.addCustomerIntoList(customer2);
        gdt.addCustomerIntoList(customer3);
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @BeforeEach
    public void tearDown() {
        System.setOut(standardOut);
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
        LocalDate today = LocalDate.of(2021, 10, 07);
        assertTrue(gdt.checkCustomersStatus(customer1, today) == 1);
        assertTrue(gdt.checkCustomersStatus(null, today) == 0);
        assertTrue(gdt.checkCustomersStatus(customer3, today) == 2);
    }

    @Test
    void printExistingCustomersStatus(){
        LocalDate today = LocalDate.of(2021, 10, 07);
        gdt.printCustomersStatus(customer1, today);
        assertEquals("customer 1 is a registered member.", outputStreamCaptor.toString()
                .trim());
    }

    @Test
    void printExpiredCustomersStatus(){
        LocalDate today = LocalDate.of(2021, 10, 07);
        gdt.printCustomersStatus(customer3, today);
        assertEquals("customer 3's pass has expired.", outputStreamCaptor.toString()
                .trim());
    }

    @Test
    void printNotExistingCustomersStatus() {
        LocalDate today = LocalDate.of(2021, 10, 07);
        gdt.printCustomersStatus(null, today);
        assertEquals("There is no such customer in the list.", outputStreamCaptor.toString()
                .trim());
    }

    @Test
    void createCustomerRecord(){
        LocalDate today = LocalDate.of(2021, 10, 07);
        assertTrue(gdt.createCustomerRecord(customer1, today) == true);
        assertTrue(gdt.createCustomerRecord(customer3, today) == false);
        assertTrue(gdt.createCustomerRecord(null, today) == false);
    }

    @Test
    void printCustomerRecord(){
        String name = "customer5";
        String nameNotExisting = "customer 5";
        assertTrue(gdt.printCustomerRecord(name) == true);
        assertTrue(gdt.printCustomerRecord(nameNotExisting) == false);
    }



}