package test.java.testjava.model.service;

import test.java.testjava.controller.pojo.AllCustomers;
import test.java.testjava.controller.pojo.Customer;
import test.java.testjava.model.CustomerEntity;

import java.util.Optional;

public interface CustomerModelService {

    Customer findById(Long id);

    AllCustomers getAllCustomers();

    Customer findByNameAndAddress(String name, String address);

    void updateCustomer(Customer customer, Long id);

    void addCustomer(Customer customer);

    void deleteCustomer(Long id);
}
