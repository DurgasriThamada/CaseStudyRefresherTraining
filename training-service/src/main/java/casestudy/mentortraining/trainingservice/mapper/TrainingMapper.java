package casestudy.mentortraining.trainingservice.mapper;

import casestudy.mentortraining.trainingservice.dto.TrainingDto;
import casestudy.mentortraining.trainingservice.entity.Training;

public class TrainingMapper {
    public static Training mapToTraining(TrainingDto trainingDto){
        return new Training(
                trainingDto.getTrainingId(),
                trainingDto.getTrainingName(),
                trainingDto.getMentorId(),
                trainingDto.getStartingDate(),
                trainingDto.getEndingDate(),
                trainingDto.getHoursPerDay()
        );
    }

    public static TrainingDto mapToTrainingDto(Training training){
        return new TrainingDto(
                training.getTrainingId(),
                training.getTrainingName(),
                training.getMentorId(),
                training.getStartingDate(),
                training.getEndingDate(),
                training.getHoursPerDay()
        );
    }
}
