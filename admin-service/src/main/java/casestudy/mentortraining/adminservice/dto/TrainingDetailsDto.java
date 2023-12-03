package casestudy.mentortraining.adminservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingDetailsDto {
    private TrainingDto trainingDto;
    private MentorDto mentorDto;
    private List<UserDto> userDtos;
}
