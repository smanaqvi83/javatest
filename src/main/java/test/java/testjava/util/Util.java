package test.java.testjava.util;

import org.springframework.stereotype.Component;
import test.java.testjava.controller.pojo.Customer;

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

}
