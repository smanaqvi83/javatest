package test.java.testjava.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import test.java.testjava.controller.pojo.AllCustomers;
import test.java.testjava.controller.pojo.Customer;
import test.java.testjava.controller.pojo.CustomerResponse;
import test.java.testjava.model.service.CustomerMongoModelService;
import test.java.testjava.util.Util;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class CustomerImpl implements CustomerInterface {

    //This service do all the interaction with file system

    @Autowired
    private Util util;

    @Autowired
    private CustomerMongoModelService customerModelService;


    @PostConstruct
    private void updateMongo() {
        log.info("In Post contruct...");
        customerModelService.addCustomer(new Customer("Address", "Name"));
    }


    @Override
    public CustomerResponse getCustomer(String id) {

        Customer customer = customerModelService.findById(id);
        if (customer != null) {
            return new CustomerResponse("Customer fetched successfully", customer);
        } else {
            return new CustomerResponse("Customer not found against ID " + id, null);
        }

    }

    @Override
    public boolean deleteCustomer(String id) {
        customerModelService.deleteCustomer(id);
        return true;
    }

    @Override
    public boolean updateCustomer(Customer customer, String id) {

        customerModelService.updateCustomer(customer, id);

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
