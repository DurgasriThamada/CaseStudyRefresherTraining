package casestudy.mentortraining.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MentorDto {
    private int id;
    @NotEmpty(message = "Mentor name should not be empty")
    private String name;
    @NotEmpty(message = "Email(Username) should not be empty")
    @Email(message = "email should be in correct manner. Ex: abc@gmail.com")
    private String emailId;
}
