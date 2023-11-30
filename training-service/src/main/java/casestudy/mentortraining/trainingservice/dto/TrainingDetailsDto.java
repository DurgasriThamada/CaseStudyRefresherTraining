package casestudy.mentortraining.trainingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingDetailsDto {
    private TrainingDto trainingDto;
    private MentorDto mentorDto;
    private List<UserDto> userDtos;
}
