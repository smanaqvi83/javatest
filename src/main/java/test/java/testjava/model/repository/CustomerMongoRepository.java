package test.java.testjava.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import test.java.testjava.model.CustomerEntity;
import test.java.testjava.model.CustomerEntityMongo;

@Repository
public interface CustomerMongoRepository extends MongoRepository<CustomerEntityMongo, String> {
}
