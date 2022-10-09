package test.java.testjava.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import test.java.testjava.controller.pojo.Customer;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Util {

    public Map<String, Customer> getMapCustomer() {
        return mapCustomer;
    }
    public void setMapCustomer(Map<String, Customer> mapCustomer) {
        this.mapCustomer = mapCustomer;
    }
    private Map<String, Customer> mapCustomer = new HashMap<>();
    public Customer addCustomer(String id, Customer customer) {
        Map<String,Customer> mapNewCustomer = this.getMapCustomer();
        return mapNewCustomer.put(id, customer);
    }
    public Customer getCustomer(String id) {
        return this.getMapCustomer().get(id);
    }
    public Collection<Customer> getAllCustomers() {
        return this.getMapCustomer().values();
    }


    @PostConstruct
    private void addDefaultCustomers() {
        Customer c1 = new Customer("PK","ABC","1");
        this.addCustomer("1", c1);
        Customer c2 = new Customer("US","DEF","2");
        this.addCustomer("2", c2);
        Customer c3 = new Customer("IN","GHI","3");
        this.addCustomer("3", c3);
        Customer c4 = new Customer("UAE","JKL","4");
        this.addCustomer("4", c4);

    }

}
