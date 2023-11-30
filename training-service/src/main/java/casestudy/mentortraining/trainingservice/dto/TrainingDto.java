package casestudy.mentortraining.trainingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingDto {
    private int trainingId;
    private String trainingName;
    private int mentorId;
    private LocalDate startingDate;
    private LocalDate endingDate;
    private int hoursPerDay;
}
