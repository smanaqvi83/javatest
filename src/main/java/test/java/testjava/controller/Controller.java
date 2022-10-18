package test.java.testjava.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import test.java.testjava.config.AppConfigs;
import test.java.testjava.config.JwtTokenUtil;
import test.java.testjava.controller.pojo.*;
import test.java.testjava.service.CustomerInterface;
import test.java.testjava.service.TokenUserDetailsImpl;


@RestController
@Slf4j
public class Controller {
    @Autowired
    private CustomerInterface customerInterface;

    @Autowired
    private AppConfigs appConfigs;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil tokenUtil;

    @Autowired
    private TokenUserDetailsImpl tokenUserDetails;
    @RequestMapping("/authenticate")
    public ResponseEntity<?> createAuthenticateToken(@RequestBody AuthenticateRequest authRequest) throws Exception {
        authenticate(authRequest.getUserName(), authRequest.getPassword());
        final UserDetails userDetails = tokenUserDetails.loadUserByUsername(authRequest.getUserName());

        final String token = tokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(token));

    }


    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException ex) {
            throw new Exception("USER is disabled", ex);
        } catch (BadCredentialsException ex) {
            throw new Exception("Invalid credentials", ex);
        }
    }

    @PostMapping("/customer")
    public ResponseEntity<CustomerResponse> saveCustomer(@RequestBody Customer customer) {
        validateCustomer(customer);

        customer.setName(customer.getName() + " - " + appConfigs.getBranchCode());
        return customerInterface.createNewCustomer(customer);
    }

    private void validateCustomer(Customer customer) {
        if (StringUtils.isBlank(customer.getName())) {
            throw new RuntimeException("Customer name is required.");
        }
    }

    @GetMapping(value = {"/","/customer"})
    public AllCustomers getAllCustomers() {
        return customerInterface.getAllCustomers();
    }



    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerResponse> getCustomerAgainstId(@PathVariable String id) {
        CustomerResponse customerResponse = customerInterface.getCustomer(id);
        return new ResponseEntity(customerInterface.getCustomer(id), HttpStatus.OK);
    }

    @GetMapping("/find-customer")
    public ResponseEntity<CustomerResponse> getCustomerAgainstId(@RequestParam String name, @RequestParam String address) {
        return customerInterface.findCustomersByNameAndAddress(name, address);
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer, @PathVariable String id) {

        validateId(id);
        boolean isUpdated = customerInterface.updateCustomer(customer, id);
        if (isUpdated) {
            return new ResponseEntity("Customer updated.", HttpStatus.OK);
        } else {
            return new ResponseEntity(new Message("Customer not found"), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable String id) {
        boolean isDeleted = customerInterface.deleteCustomer(id);
        if (isDeleted) {
            return new ResponseEntity("Customer deleted.", HttpStatus.OK);
        } else {
            return new ResponseEntity(new Message("Delete is not successful."), HttpStatus.NOT_FOUND);
        }
    }

    private void validateId(String id) {
        if (StringUtils.isBlank(id) || !NumberUtils.isDigits(id)) {
            throw new RuntimeException("ID should be valid.");
        }
    }
}
