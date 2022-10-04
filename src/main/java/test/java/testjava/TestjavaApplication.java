package test.java.testjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class TestjavaApplication {

	public static void main(String[] args) {
		configureTimeZone();
		SpringApplication.run(TestjavaApplication.class, args);
	}


	private static void configureTimeZone() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

}
