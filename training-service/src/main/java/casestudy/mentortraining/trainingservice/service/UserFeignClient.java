package casestudy.mentortraining.trainingservice.service;

import casestudy.mentortraining.trainingservice.dto.MentorDto;
import casestudy.mentortraining.trainingservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(url = "http://localhost:8082", value = "USER-SERVICE")
public interface UserFeignClient {
    @GetMapping("/getAllUsersByTrainingId/{id}")
    public List<UserDto> getAllUsersByTrainingId(@PathVariable("id") int trainingId);
}
