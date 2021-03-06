package uppgift1;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Gym {
    private Recorder recorder;
    private List<Customer> customerList;

    public Gym() {
        this.recorder = new Recorder();
        this.customerList = new ArrayList<>();
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public List<Customer> generateCustomerListFromFile(String fileName){
        List<Customer> customerList = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
            while(true){
                String currentIdAndName = br.readLine();

                if( currentIdAndName == null){
                    break;
                }

                if(currentIdAndName.equals("")){
                    continue;
                }

                String[] IdAndName = currentIdAndName.split(",");
                long id = Long.parseLong(IdAndName[0].trim());
                String name = IdAndName[1].trim();
                String latestPaymentDate = br.readLine();
                customerList.add(new Customer(id, name, latestPaymentDate));
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        return customerList;
    }

    public void addCustomerIntoList(Customer customer){
        if(!this.customerList.contains(customer)) {
            this.customerList.add(customer);
        }
    }

    public Customer findCustomer(long id){
        try {
            return customerList
                    .stream()
                    .filter(currentCustomer -> currentCustomer.getId() == id)
                    .findFirst().get();
        } catch (NoSuchElementException e){
            return null;
        }
    }

    // should return an array of Customer
    // because one Customer cannot be identified by only her/his name
    // in case there are multiple persons having the same name
    public Customer findCustomer(String name){
        try {
            return customerList
                    .stream()
                    .filter(currentCustomer -> currentCustomer.getName().equals(name))
                    .findFirst().get();
        }catch (NoSuchElementException e){
            return null;
        }
    }

    public int checkCustomersStatus(Customer customer, LocalDate today){
        if(customer == null){
            return 0;
        }

        String customersLatestPaymentDate = customer.getLatestPaymentDate();

        boolean isMember = DateHandler.isTimeDiffWithin365Days(DateHandler.checkTimeDiff(customersLatestPaymentDate, today.toString()));
        if(isMember){
            return 1;
        } else {
            return 2;
        }
    }

    public void printCustomersStatus(Customer customer, int statusNumber){
        switch (statusNumber){
            case 0:
                System.out.println("There is no such customer in the list.");
                break;
            case 1:
                System.out.println(customer.getName() + " is a registered member.");
                break;
            case 2:
                System.out.println(customer.getName() + "'s pass has expired.");
                break;
            default:
                break;
        }
    }

    public boolean createCustomerRecord(Customer customer, int statusNumber){
        switch (statusNumber){
            case 0:
                System.out.println("There is no such customer in the list.");
                return false;
            case 1:
                boolean isSuccess = recorder.createRecord(customer.getName(), customer.getId());
                if(!isSuccess){
                    return false;
                }
                System.out.println(customer.getName() + "'s record has been successfully created for today.");
                return true;
            case 2:
                System.out.println(customer.getName() + "'s pass has expired.");
                return false;
            default:
                return false;
        }
    }

    public boolean printCustomerRecord(String customerName) {
        boolean isSuccess = recorder.printRecord(customerName);
        if(!isSuccess){
            System.out.println("There is no record for " + customerName + ".");
            return false;
        }
        return true;
    }

}
