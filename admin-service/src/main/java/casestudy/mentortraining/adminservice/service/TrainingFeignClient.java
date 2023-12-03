package casestudy.mentortraining.adminservice.service;

import casestudy.mentortraining.adminservice.dto.TrainingDto;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "http://localhost:8081", value = "TRAINING-SERVICE")
public interface TrainingFeignClient {
    @GetMapping("api/trainings/{id}")
    public TrainingDto getTrainingById(@PathVariable("id") int trainingId);

    @PostMapping("api/trainings/createTraining")
    public TrainingDto createTraining(@Valid @RequestBody TrainingDto trainingDto);
}
