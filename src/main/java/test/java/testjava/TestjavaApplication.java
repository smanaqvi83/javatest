package test.java.testjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import test.java.testjava.config.AppConfigs;
import test.java.testjava.model.repository.CustomerMongoRepository;

import java.util.TimeZone;

@SpringBootApplication
@EnableConfigurationProperties(value = {AppConfigs.class})
@EnableMongoRepositories
public class TestjavaApplication {

	public static void main(String[] args) {
		configureTimeZone();
		SpringApplication.run(TestjavaApplication.class, args);
	}


	private static void configureTimeZone() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

}
