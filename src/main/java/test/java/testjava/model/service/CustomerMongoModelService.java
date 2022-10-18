package test.java.testjava.model.service;

import test.java.testjava.controller.pojo.AllCustomers;
import test.java.testjava.controller.pojo.Customer;

public interface CustomerMongoModelService {

    Customer findById(String id);

    AllCustomers getAllCustomers();

    Customer findByNameAndAddress(String name, String address);

    void updateCustomer(Customer customer, String id);

    void addCustomer(Customer customer);

    void deleteCustomer(String id);
}
