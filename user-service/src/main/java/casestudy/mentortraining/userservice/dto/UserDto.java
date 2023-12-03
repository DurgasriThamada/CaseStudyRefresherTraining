package casestudy.mentortraining.userservice.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private int userId;
    @NotEmpty
    private String userName;
    @NotEmpty
    private String emailId;
    @NotNull
    private int trainingId;
}

