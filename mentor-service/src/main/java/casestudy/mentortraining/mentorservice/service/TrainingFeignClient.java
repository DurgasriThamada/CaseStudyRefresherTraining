package casestudy.mentortraining.mentorservice.service;

import casestudy.mentortraining.mentorservice.dto.TrainingDetailsDto;
import casestudy.mentortraining.mentorservice.dto.TrainingDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(url = "http://localhost:8081", value = "TRAINING-SERVICE")
public interface TrainingFeignClient {
    @GetMapping("/api/trainings/getTrainingDetails/{id}")
    public TrainingDetailsDto getCompleteTrainingDetailsAlongWithAssociatedUsers(@PathVariable("id") int trainingId);

    @GetMapping("/api/trainings/getTrainingByMentorId/{id}")
    public List<TrainingDto> getTrainingListByMentorId(@PathVariable("id") int mentorId);

}
