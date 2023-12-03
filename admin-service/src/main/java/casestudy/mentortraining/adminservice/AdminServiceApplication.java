package casestudy.mentortraining.adminservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableFeignClients

public class AdminServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(AdminServiceApplication.class, args);
	}

}
