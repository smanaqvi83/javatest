package test.java.testjava.service;

import org.springframework.http.ResponseEntity;
import test.java.testjava.controller.pojo.AllCustomers;
import test.java.testjava.controller.pojo.Customer;
import test.java.testjava.controller.pojo.CustomerResponse;

public interface CustomerInterface {

    CustomerResponse getCustomer(String id);

    boolean deleteCustomer(String id);

    boolean updateCustomer(Customer customer, String id);

    ResponseEntity<CustomerResponse> createNewCustomer(Customer customer);

    AllCustomers getAllCustomers();


    ResponseEntity<CustomerResponse> findCustomersByNameAndAddress(String name, String address);
}
