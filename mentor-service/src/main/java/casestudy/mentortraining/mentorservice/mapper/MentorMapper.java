package casestudy.mentortraining.mentorservice.mapper;

import casestudy.mentortraining.mentorservice.dto.MentorDto;
import casestudy.mentortraining.mentorservice.entity.Mentor;

public class MentorMapper {

    public static MentorDto mapToMentorDto(Mentor mentor){
        return new MentorDto(
                mentor.getId(),
                mentor.getName(),
                mentor.getEmailId()
        );
    }

    public static Mentor mapToMentor(MentorDto mentorDto){

        Mentor mentor = new Mentor();
        mentor.setId(mentorDto.getId());
        mentor.setName(mentorDto.getName());
        mentor.setEmailId(mentorDto.getEmailId());

        return mentor;
    }
}
