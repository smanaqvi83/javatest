package test.java.testjava.model.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import test.java.testjava.model.CustomerEntity;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {

    //Method Query
    Optional<CustomerEntity> findByNameAndAddress(String name, String address);
    @Query("SELECT u FROM CustomerEntity u WHERE u.name = :name and u.address = :address")
    Optional<CustomerEntity> findByCustomNameAndAddress(@Param("name") String name, @Param("address") String address);

    @Query(value = "SELECT * FROM CustomerEntity u WHERE u.name = :name and u.address = :address", nativeQuery = true)
    Optional<CustomerEntity> findByCustomNameAndAddressNativeQuery(@Param("name") String name, @Param("address") String address);


    @Query("SELECT c FROM CustomerEntity c")
    Collection<CustomerEntity> findAllCustomer();

    @Query(value = "SELECT * FROM customers", nativeQuery = true)
    Collection<CustomerEntity> selectAllCustomers();

    Collection<CustomerEntity> findAll(Sort by);


}
