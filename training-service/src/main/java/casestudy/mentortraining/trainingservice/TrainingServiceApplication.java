package casestudy.mentortraining.trainingservice;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.CrossOrigin;

@OpenAPIDefinition(
		info = @Info(
				title = "Training-service REST API's",
				description = "Training-service REST API's documentation",
				contact = @Contact(
						name = "Durga",
						email = "durga@gmail.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Training-service doc"
		)
)
@SpringBootApplication
@EnableFeignClients

public class TrainingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainingServiceApplication.class, args);
	}

}
