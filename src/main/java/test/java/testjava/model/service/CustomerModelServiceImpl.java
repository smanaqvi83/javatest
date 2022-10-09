package test.java.testjava.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.java.testjava.controller.pojo.AllCustomers;
import test.java.testjava.controller.pojo.Customer;
import test.java.testjava.model.CustomerEntity;
import test.java.testjava.model.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerModelServiceImpl implements CustomerModelService {

    @Autowired
    private CustomerRepository repository;


    @Override
    public Customer findById(Long id) {
        Optional<CustomerEntity> optionalCustomerEntity = repository.findById(id);

        Customer customer = null;
        if (optionalCustomerEntity.isPresent()) {
            CustomerEntity customerEntity = optionalCustomerEntity.get();
            customer = getCustomerFromCustomerEntity(customerEntity);
        }

        return customer;
    }

    @Override
    public AllCustomers getAllCustomers() {
        List<Customer> lstCustomer = new ArrayList<Customer>();
        for (CustomerEntity cust: repository.findAll()) {
            Customer customer = getCustomerFromCustomerEntity(cust);
            lstCustomer.add(customer);
        }
        return new AllCustomers(lstCustomer);
    }

    @Override
    public Customer findByNameAndAddress(String name, String address) {
        Optional<CustomerEntity> optionalCustomerEntity = repository.findByNameAndAddress(name, address);
        Customer customer = null;
        if (optionalCustomerEntity.isPresent()) {
            customer = getCustomerFromCustomerEntity(optionalCustomerEntity.get());
        }

        return customer;
    }

    @Override
    public void updateCustomer(Customer customer, Long id) {
        Optional<CustomerEntity> optionalCustomerEntity = repository.findById(id);
        if (optionalCustomerEntity.isPresent()) {
            CustomerEntity customerEntity = optionalCustomerEntity.get();
            customerEntity.setAddress(customer.getAddress());
            customerEntity.setName(customer.getName());
            repository.save(customerEntity);
        } else {
            throw new RuntimeException("No customer found with this id.");
        }
    }

    @Override
    public void addCustomer(Customer customer) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setAddress(customer.getAddress());
        customerEntity.setName(customer.getName());
        repository.save(customerEntity);
    }

    public void deleteCustomer(Long id) {
        repository.deleteById(id);
    }

    private Customer getCustomerFromCustomerEntity(CustomerEntity customerEntity) {
        Customer customer = new Customer();
        customer.setId(String.valueOf(customerEntity.getId()));
        customer.setName(customerEntity.getName());
        customer.setAddress(customerEntity.getAddress());
        return customer;
    }
}
