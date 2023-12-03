package casestudy.mentortraining.adminservice.service;

import casestudy.mentortraining.adminservice.dto.MentorDetailsDto;

import java.util.List;

public interface AdminService {
    public List<MentorDetailsDto> getCompleteMentorDetailsALongWithTrainingsAndItsUsers();
}
