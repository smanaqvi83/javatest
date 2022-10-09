package test.java.testjava.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import test.java.testjava.model.CustomerEntity;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {

    //Method Query
    Optional<CustomerEntity> findByNameAndAddress(String name, String address);

}
