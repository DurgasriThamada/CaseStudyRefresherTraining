package casestudy.mentortraining.userservice.service;


import casestudy.mentortraining.userservice.dto.MentorDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8080", value = "MENTOR-SERVICE")
public interface MentorFeignClient {
    @GetMapping("api/mentors/{id}")
    public MentorDto getMentorById(@PathVariable("id") int mentorId);
}
