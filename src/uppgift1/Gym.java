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

    public Recorder getRecorder() {
        return recorder;
    }

    public void setRecorder(Recorder recorder) {
        this.recorder = recorder;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public List<Customer> generateCustomerListFromFile(String fileName){
        List<Customer> customerList = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(fileName));){
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
                customerList.add(new Customer(id, name, latestPaymentDate));;
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

    public String findCustomersLatestPayment(long id){
        if(findCustomer(id) == null){
            return null;
        }
        return findCustomer(id).getLatestPaymentDate();
    }

    public String findCustomersLatestPayment(String name){
        if(findCustomer(name) == null){
            return null;
        }
        return findCustomer(name).getLatestPaymentDate();
    }

    public String checkCustomersStatus(long id, LocalDate today){
        String customersLatestPaymentDate = findCustomersLatestPayment(id);

        if(customersLatestPaymentDate == null){
            return id + " is not registered yet.";
        }

        boolean isMember = DateHandler.checkTimeDiff(
                DateHandler.removeHyphenFromDate(customersLatestPaymentDate),
                DateHandler.removeHyphenFromDate(today.toString())
                );
        if(isMember){
            return findCustomer(id).getName() + " is a member.";
        } else {
            return findCustomer(id).getName()+ "'s pass is not validate.";
        }
    }

    public String checkCustomersStatus(String name, LocalDate today){
        String customersLatestPaymentDate = findCustomersLatestPayment(name);

        if(customersLatestPaymentDate == null){
            return name + " is not registered yet.";
        }

        boolean isMember = DateHandler.checkTimeDiff(
                DateHandler.removeHyphenFromDate(customersLatestPaymentDate),
                DateHandler.removeHyphenFromDate(today.toString())
        );
        if(isMember){
            return findCustomer(name).getName() + " is a member.";
        } else {
            return findCustomer(name).getName()+ "'s pass is not validate.";
        }
    }

    public boolean createCustomerRecord(String customerName){
        long customerId = -1;
        for(Customer currentCustomer : customerList){
            if(currentCustomer.getName().equals(customerName)){
                customerId = currentCustomer.getId();
            }
        }
        if(customerId == -1){
            System.out.println(customerName + " is not registered yet.");
            return false;
        }
        recorder.createRecord(customerName, customerId);
        return true;
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
