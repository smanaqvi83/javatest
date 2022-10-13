package test.java.testjava.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Document(value = "customersentity")
@Data
public class CustomerEntityMongo {
    @Id
    private long id;
    private String name;
    private String address;
}
