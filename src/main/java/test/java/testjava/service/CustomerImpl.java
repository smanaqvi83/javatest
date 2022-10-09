package test.java.testjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import test.java.testjava.controller.pojo.AllCustomers;
import test.java.testjava.controller.pojo.Customer;
import test.java.testjava.controller.pojo.CustomerResponse;
import test.java.testjava.model.service.CustomerModelService;
import test.java.testjava.util.Util;

@Service
public class CustomerImpl implements CustomerInterface {

    //This service do all the interaction with file system

    @Autowired
    private Util util;

    @Autowired
    private CustomerModelService customerModelService;


    @Override
    public CustomerResponse getCustomer(String id) {

        Customer customer = customerModelService.findById(Long.parseLong(id));
        if (customer != null) {
            return new CustomerResponse("Customer fetched successfully", customer);
        } else {
            return new CustomerResponse("Customer not found against ID " + id, null);
        }

    }

    @Override
    public boolean deleteCustomer(String id) {
        customerModelService.deleteCustomer(Long.parseLong(id));
        return true;
    }

    @Override
    public boolean updateCustomer(Customer customer, String id) {

        customerModelService.updateCustomer(customer, Long.parseLong(id));

        return true;

    }

    @Override
    public ResponseEntity<CustomerResponse> createNewCustomer(Customer customer) {
        customerModelService.addCustomer(customer);
         return new ResponseEntity<>(new CustomerResponse("Customer saved successfully"), HttpStatus.CREATED);
    }

    @Override
    public AllCustomers getAllCustomers() {
        return customerModelService.getAllCustomers();
    }

    @Override
    public ResponseEntity<CustomerResponse> findCustomersByNameAndAddress(String name, String address) {
        Customer customer = customerModelService.findByNameAndAddress(name, address);
        return new ResponseEntity<>( new CustomerResponse("customer fetched successfully", customer), HttpStatus.OK);
    }



}
