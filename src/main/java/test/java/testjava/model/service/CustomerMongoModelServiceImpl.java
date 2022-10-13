package test.java.testjava.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.java.testjava.controller.pojo.AllCustomers;
import test.java.testjava.controller.pojo.Customer;
import test.java.testjava.model.CustomerEntity;
import test.java.testjava.model.CustomerEntityMongo;
import test.java.testjava.model.repository.CustomerMongoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerMongoModelServiceImpl implements CustomerMongoModelService {

    @Autowired
    private CustomerMongoRepository repository;


    @Override
    public Customer findById(Long id) {
        Optional<CustomerEntityMongo> optionalCustomerEntity = repository.findById(id);

        Customer customer = null;
        if (optionalCustomerEntity.isPresent()) {
            CustomerEntityMongo customerEntity = optionalCustomerEntity.get();
            customer = getCustomerFromCustomerEntity(customerEntity);
        }

        return customer;
    }

    @Override
    public AllCustomers getAllCustomers() {
        List<Customer> lstCustomer = new ArrayList<Customer>();
//        for (CustomerEntity cust: repository.findAll(Sort.by(Sort.Direction.ASC,"name"))) {
        for (CustomerEntityMongo cust: repository.findAll()) {
            Customer customer = getCustomerFromCustomerEntity(cust);
            lstCustomer.add(customer);
        }
        return new AllCustomers(lstCustomer);
    }

    @Override
    public Customer findByNameAndAddress(String name, String address) {
//        Optional<CustomerEntity> optionalCustomerEntity = repository.findByNameAndAddress(name, address);
//        Customer customer = null;
//        if (optionalCustomerEntity.isPresent()) {
//            customer = getCustomerFromCustomerEntity(optionalCustomerEntity.get());
//        }

        return null;
    }

    @Override
    public void updateCustomer(Customer customer, Long id) {
        Optional<CustomerEntityMongo> optionalCustomerEntity = repository.findById(id);
        if (optionalCustomerEntity.isPresent()) {
            CustomerEntityMongo customerEntity = optionalCustomerEntity.get();
            customerEntity.setAddress(customer.getAddress());
            customerEntity.setName(customer.getName());
            repository.save(customerEntity);
        } else {
            throw new RuntimeException("No customer found with this id.");
        }
    }

    @Override
    public void addCustomer(Customer customer) {
        CustomerEntityMongo customerEntity = new CustomerEntityMongo();
        customerEntity.setAddress(customer.getAddress());
        customerEntity.setName(customer.getName());
        repository.save(customerEntity);
    }

    public void deleteCustomer(Long id) {
        repository.deleteById(id);
    }

    private Customer getCustomerFromCustomerEntity(CustomerEntityMongo customerEntity) {
        Customer customer = new Customer();
        customer.setId(String.valueOf(customerEntity.getId()));
        customer.setName(customerEntity.getName());
        customer.setAddress(customerEntity.getAddress());
        return customer;
    }
}
