package casestudy.mentortraining.mentorservice;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "Mentor-service REST API's",
				description = "Mentor-service REST API's documentation",
				contact = @Contact(
						name = "Durga",
						email = "durga@gmail.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Mentor-service doc"
		)
)
@SpringBootApplication
public class MentorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MentorServiceApplication.class, args);
	}

}
