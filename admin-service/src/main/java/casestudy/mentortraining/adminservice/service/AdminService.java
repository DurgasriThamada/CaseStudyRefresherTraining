package casestudy.mentortraining.adminservice.service;

import casestudy.mentortraining.adminservice.dto.MentorDetailsDto;
import casestudy.mentortraining.adminservice.dto.TrainingDetailsDto;
import casestudy.mentortraining.adminservice.dto.TrainingDto;
import casestudy.mentortraining.adminservice.dto.UserDto;

import java.util.List;

public interface AdminService {
    public List<MentorDetailsDto> getCompleteMentorDetailsALongWithTrainingsAndItsUsers();

    public TrainingDetailsDto allocateBatchToMentor(TrainingDetailsDto trainingDetailsDto);
}
