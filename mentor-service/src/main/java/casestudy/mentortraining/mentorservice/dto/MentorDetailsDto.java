package casestudy.mentortraining.mentorservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MentorDetailsDto {
    private MentorDto mentorDto;
    private List<TrainingUserDto> trainingDtoList;
}
