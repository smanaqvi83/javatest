package test.java.testjava.controller;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.java.testjava.controller.pojo.AllCustomers;
import test.java.testjava.controller.pojo.Customer;
import test.java.testjava.controller.pojo.CustomerResponse;
import test.java.testjava.service.CustomerInterface;


@RestController
public class Controller {
    @Autowired
    private CustomerInterface customerInterface;

    @PostMapping("/customer")
    public ResponseEntity<CustomerResponse> saveCustomer(@RequestBody Customer customer) {
        boolean isSuccess = customerInterface.createNewCustomer(customer);
        if (isSuccess) {
            return new ResponseEntity(new CustomerResponse("Customer saved successfully"), HttpStatus.OK);
        } else {
            return new ResponseEntity(new CustomerResponse("Customer not saved"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/customer")
    public AllCustomers getAllCustomers() {
        return customerInterface.getAllCustomers();
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerResponse> getCustomerAgainstId(@PathVariable String id) {
        CustomerResponse customerResponse = customerInterface.getCustomer(id);
        return new ResponseEntity(customerInterface.getCustomer(id), HttpStatus.OK);
    }

    @PutMapping("/customer/{id}")
    public String updateCustomer(@RequestBody Customer customer, @PathVariable String id) {
        System.out.println("Updating customer " + customer);
        return "Getting customer against ID";
    }

    @DeleteMapping("/customer/{id}")
    public String deleteCustomer(@PathVariable String id) {
        System.out.println("Deleting customer " + id);
        return "Getting customer against ID";
    }
}
