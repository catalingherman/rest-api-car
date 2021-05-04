package internship.rest.car;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class CarApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarApplication.class, args);
		log.info("The server is running on http://localhost:8070");
	}

}
