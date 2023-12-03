package casestudy.mentortraining.adminservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingUserDto {
    private TrainingDto trainingDto;
    private List<UserDto> userDtoList;
}
