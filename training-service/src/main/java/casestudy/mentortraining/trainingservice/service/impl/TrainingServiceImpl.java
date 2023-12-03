package casestudy.mentortraining.trainingservice.service.impl;

import casestudy.mentortraining.trainingservice.dto.MentorDto;
import casestudy.mentortraining.trainingservice.dto.TrainingDetailsDto;
import casestudy.mentortraining.trainingservice.dto.TrainingDto;
import casestudy.mentortraining.trainingservice.dto.UserDto;
import casestudy.mentortraining.trainingservice.entity.Training;
import casestudy.mentortraining.trainingservice.exception.ResourceDoesNotExistException;
import casestudy.mentortraining.trainingservice.mapper.TrainingMapper;
import casestudy.mentortraining.trainingservice.repository.TrainingRepository;
import casestudy.mentortraining.trainingservice.service.MentorFeignClient;
import casestudy.mentortraining.trainingservice.service.TrainingService;
import casestudy.mentortraining.trainingservice.service.UserFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TrainingServiceImpl implements TrainingService {

    private TrainingRepository trainingRepository;

    private MentorFeignClient mentorFeignClient;
    private UserFeignClient userFeignClient;
    @Override
    public TrainingDto createTraining(TrainingDto trainingDto) {
        Optional<MentorDto> mentorDto = Optional.ofNullable(mentorFeignClient.getMentorById(trainingDto.getMentorId()));
        if(mentorDto.isEmpty())
            throw new ResourceDoesNotExistException("Mentor");
        Training training = TrainingMapper.mapToTraining(trainingDto);
        Training savedTraining = trainingRepository.save(training);

        return TrainingMapper.mapToTrainingDto(savedTraining);
    }

    @Override
    public List<TrainingDto> getAllTraining() {
        List<Training> trainings = trainingRepository.findAll();
        List<TrainingDto> trainingDtos = trainings.stream()
                .map((training)->TrainingMapper.mapToTrainingDto(training))
                .collect(Collectors.toList());
        return trainingDtos;
    }

    @Override
    public List<TrainingDto> getTrainingListByMentorId(int mentorId) {
        Optional<MentorDto> mentorDto = Optional.ofNullable(mentorFeignClient.getMentorById(mentorId));
        if(mentorDto.isEmpty())
            throw new ResourceDoesNotExistException("Mentor");
        List<Training> trainings = trainingRepository.findByMentorId(mentorId);
        List<TrainingDto> trainingDtos = trainings.stream()
                .map((training)->TrainingMapper.mapToTrainingDto(training))
                .collect(Collectors.toList());
        return trainingDtos;
    }

    @Override
    public TrainingDto getByTrainingId(int trainingId) {
        Optional<Training> training = trainingRepository.findById(trainingId);
        if(training.isEmpty())
            throw new ResourceDoesNotExistException("Training");
        return TrainingMapper.mapToTrainingDto(training.get());
    }

    @Override
    public void deleteTraining(int trainingId) {
        Optional<Training> training = trainingRepository.findById(trainingId);
        if(training.isEmpty())
            throw new ResourceDoesNotExistException("Training");
        trainingRepository.deleteById(trainingId);
    }

    @Override
    public TrainingDto updateTraining(Training training) {
        Training existingTraining = trainingRepository.findById(training.getTrainingId()).orElseThrow(
                ()-> new ResourceDoesNotExistException("Training")
        );
        existingTraining.setTrainingName(training.getTrainingName());
        existingTraining.setEndingDate(training.getEndingDate());
        existingTraining.setHoursPerDay(training.getHoursPerDay());
        existingTraining.setStartingDate(training.getStartingDate());
        existingTraining.setMentorId(training.getMentorId());
        return TrainingMapper.mapToTrainingDto(existingTraining);
    }

    @Override
    public TrainingDetailsDto getCompleteTrainingDetailsAlongWithAssociatedUsers(int trainingId) {
        Training existingTraining = trainingRepository.findById(trainingId).orElseThrow(
                ()-> new ResourceDoesNotExistException("Training")
        );
        MentorDto mentorDto = mentorFeignClient.getMentorById(existingTraining.getMentorId());
        List<UserDto> userDtos = userFeignClient.getAllUsersByTrainingId(trainingId);
        return new TrainingDetailsDto(TrainingMapper.mapToTrainingDto(existingTraining),mentorDto,userDtos);
    }
}
