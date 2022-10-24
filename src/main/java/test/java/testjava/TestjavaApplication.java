package test.java.testjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import test.java.testjava.config.AppConfigs;
import test.java.testjava.model.repository.CustomerMongoRepository;

import java.util.TimeZone;

@SpringBootApplication
@EnableConfigurationProperties(value = {AppConfigs.class})
@EnableMongoRepositories
@EnableSwagger2
@EnableEurekaClient
public class TestjavaApplication {

	public static void main(String[] args) {
		configureTimeZone();
		SpringApplication.run(TestjavaApplication.class, args);
	}


	private static void configureTimeZone() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("test.java.testjava"))
				.build();
	}

}
