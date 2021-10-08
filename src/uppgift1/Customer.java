package uppgift1;

import java.util.Objects;

public class Customer {
    private long id;
    private String name;
    private String latestPaymentDate;

    public Customer(long id, String name, String latestPaymentDate) {
        this.id = id;
        this.name = name;
        this.latestPaymentDate = latestPaymentDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatestPaymentDate() {
        return latestPaymentDate;
    }

    public void setLatestPaymentDate(String latestPaymentDate) {
        this.latestPaymentDate = latestPaymentDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.getId() && Objects.equals(name, customer.getName()) && Objects.equals(latestPaymentDate, customer.getLatestPaymentDate());
    }


    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", latestPaymentDate='" + latestPaymentDate + '\'' +
                '}';
    }
}
