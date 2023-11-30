package casestudy.mentortraining.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDto {
    private UserDto userDto;
    private MentorDto mentorDto;
    private TrainingDto trainingDto;
}
