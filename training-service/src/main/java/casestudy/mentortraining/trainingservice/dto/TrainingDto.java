package casestudy.mentortraining.trainingservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotEmpty
    private String trainingName;
    @NotNull
    private int mentorId;
    @NotNull
    private LocalDate startingDate;
    @NotNull
    private LocalDate endingDate;
    @NotNull
    private int hoursPerDay;
}
