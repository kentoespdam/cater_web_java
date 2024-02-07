package id.perumdamts.cater;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class CaterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaterApplication.class, args);
	}

}
