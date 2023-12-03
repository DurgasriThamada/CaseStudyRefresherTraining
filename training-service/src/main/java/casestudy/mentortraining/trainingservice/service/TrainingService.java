package casestudy.mentortraining.trainingservice.service;

import casestudy.mentortraining.trainingservice.dto.TrainingDetailsDto;
import casestudy.mentortraining.trainingservice.dto.TrainingDto;
import casestudy.mentortraining.trainingservice.entity.Training;

import java.util.List;

public interface TrainingService {
    public TrainingDto createTraining(TrainingDto trainingDto);
    public List<TrainingDto> getAllTraining();
    public List<TrainingDto> getTrainingListByMentorId(int mentorId);
    public TrainingDto getByTrainingId(int trainingId);
    public void deleteTraining(int trainingId);
    public TrainingDto updateTraining(Training training);
    public TrainingDetailsDto getCompleteTrainingDetailsAlongWithAssociatedUsers(int trainingId);
}
