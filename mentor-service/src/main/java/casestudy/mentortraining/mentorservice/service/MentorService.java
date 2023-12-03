package casestudy.mentortraining.mentorservice.service;

import casestudy.mentortraining.mentorservice.dto.MentorDetailsDto;
import casestudy.mentortraining.mentorservice.dto.MentorDto;
import casestudy.mentortraining.mentorservice.entity.Mentor;

import java.util.List;

public interface MentorService {
    public MentorDto loginMentor(String emailId, String password);
    public MentorDto registerMentor(Mentor mentor);
    public List<MentorDto> getAllMentors();
    public MentorDto getMentorById(int mentorId);
    public void deleteMentorById(int mentorId);
    public MentorDto updateMentorById(MentorDto mentor);
    public MentorDetailsDto getCompleteMentorDetailsWithTrainingsAndUsers(int mentorId);
}
