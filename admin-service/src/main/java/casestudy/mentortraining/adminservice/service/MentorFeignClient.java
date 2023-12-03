package casestudy.mentortraining.adminservice.service;

import casestudy.mentortraining.adminservice.dto.MentorDetailsDto;
import casestudy.mentortraining.adminservice.dto.MentorDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(url = "http://localhost:8080", value = "MENTOR-SERVICE")
public interface MentorFeignClient {
    @GetMapping("/api/mentors/completeMentorDetails/{id}")
    public MentorDetailsDto getCompleteMentorDetailsWithTrainingsAndUsers(@PathVariable("id") int mentorId);

    @GetMapping("/api/mentors")
    public List<MentorDto> getAllMentors();

    @PutMapping("/api/mentors/updateMentor")
    public MentorDto updateMentor(@RequestBody MentorDto mentorDto);

    @GetMapping("/api/mentors/{id}")
    public MentorDto getMentorById(@PathVariable("id") int mentorId);
}
