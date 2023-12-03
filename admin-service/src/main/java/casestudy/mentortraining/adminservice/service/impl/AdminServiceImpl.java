package casestudy.mentortraining.adminservice.service.impl;

import casestudy.mentortraining.adminservice.dto.MentorDetailsDto;
import casestudy.mentortraining.adminservice.dto.MentorDto;
import casestudy.mentortraining.adminservice.service.AdminService;
import casestudy.mentortraining.adminservice.service.MentorFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
    private MentorFeignClient mentorFeignClient;
    @Override
    public List<MentorDetailsDto> getCompleteMentorDetailsALongWithTrainingsAndItsUsers() {
        List<MentorDto> mentors = mentorFeignClient.getAllMentors();
        List<MentorDetailsDto> mentorsList = mentors.stream()
                .map((mentor)->mentorFeignClient.getCompleteMentorDetailsWithTrainingsAndUsers(mentor.getId()))
                .collect(Collectors.toList());
        return mentorsList;
    }
}
