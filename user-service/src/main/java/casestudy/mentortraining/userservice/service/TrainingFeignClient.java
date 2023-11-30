package casestudy.mentortraining.userservice.service;

import casestudy.mentortraining.userservice.dto.MentorDto;
import casestudy.mentortraining.userservice.dto.TrainingDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8081", value = "TRAINING-SERVICE")
public interface TrainingFeignClient {
    @GetMapping("api/trainings/{id}")
    public TrainingDto getTrainingById(@PathVariable("id") int trainingId);
}
