package test.java.testjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.java.testjava.controller.pojo.AllCustomers;
import test.java.testjava.controller.pojo.Customer;
import test.java.testjava.controller.pojo.CustomerResponse;
import test.java.testjava.util.Util;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class CustomerImpl implements CustomerInterface {

    //This service do all the interaction with file system

    @Autowired
    private Util util;


    @Override
    public CustomerResponse getCustomer(String id) {
        Customer customer = util.getCustomer(id);
        if (customer != null) {
            return new CustomerResponse("Customer fetched successfully", customer);
        } else {
            return new CustomerResponse("Customer not found against ID " + id, null);
        }

    }

    @Override
    public boolean deleteCustomer(String id) {
        return false;
    }

    @Override
    public boolean updateCustomer(Customer customer, String id) {
        return false;
    }

    @Override
    public boolean createNewCustomer(Customer customer) {
         util.addCustomer(customer.getId(), customer);
        return true;
    }

    @Override
    public AllCustomers getAllCustomers() {
        return new AllCustomers(util.getAllCustomers().stream().collect(Collectors.toCollection(ArrayList::new)));
    }
}
