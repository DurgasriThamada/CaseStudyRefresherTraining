package casestudy.mentortraining.adminservice.service.impl;

import casestudy.mentortraining.adminservice.dto.*;
import casestudy.mentortraining.adminservice.service.AdminService;
import casestudy.mentortraining.adminservice.service.MentorFeignClient;
import casestudy.mentortraining.adminservice.service.TrainingFeignClient;
import casestudy.mentortraining.adminservice.service.UserFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
    private MentorFeignClient mentorFeignClient;
    private TrainingFeignClient trainingFeignClient;
    private UserFeignClient userFeignClient;
    @Override
    public List<MentorDetailsDto> getCompleteMentorDetailsALongWithTrainingsAndItsUsers() {
        List<MentorDto> mentors = mentorFeignClient.getAllMentors();
        List<MentorDetailsDto> mentorsList = mentors.stream()
                .map((mentor)->mentorFeignClient.getCompleteMentorDetailsWithTrainingsAndUsers(mentor.getId()))
                .collect(Collectors.toList());
        return mentorsList;
    }

    @Override
    public TrainingDetailsDto allocateBatchToMentor(TrainingDetailsDto trainingDetailsDto) {
        TrainingDto trainingDto = trainingDetailsDto.getTrainingDto();
        List<UserDto> users = trainingDetailsDto.getUserDtos();
        TrainingDto savedTrainingDto = trainingFeignClient.createTraining(trainingDto);
        MentorDto mentorDto = mentorFeignClient.getMentorById(trainingDto.getMentorId());
        mentorDto.setHoursAvailable(mentorDto.getHoursAvailable()-3);
        MentorDto updatedMentor = mentorFeignClient.updateMentor(mentorDto);
        for (UserDto user:users) {
            user.setTrainingId(trainingDto.getTrainingId());
        }
        List<UserDto> updatedUsers = null;
        for(UserDto user: users){
            updatedUsers.add(userFeignClient.updateUser(user));
        }
        return new TrainingDetailsDto(trainingDto,mentorDto,updatedUsers);
    }
}
