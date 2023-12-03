package casestudy.mentortraining.mentorservice.service.impl;

import casestudy.mentortraining.mentorservice.dto.*;
import casestudy.mentortraining.mentorservice.entity.Mentor;
import casestudy.mentortraining.mentorservice.exception.EmailAlreadyExistsException;
import casestudy.mentortraining.mentorservice.exception.InvalidCredentialsException;
import casestudy.mentortraining.mentorservice.exception.MentorDoesNotExistException;
import casestudy.mentortraining.mentorservice.mapper.MentorMapper;
import casestudy.mentortraining.mentorservice.repository.MentorRepository;
import casestudy.mentortraining.mentorservice.service.MentorService;
import casestudy.mentortraining.mentorservice.service.TrainingFeignClient;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MentorServiceImpl implements MentorService {

    private MentorRepository mentorRepository;

    private TrainingFeignClient trainingFeignClient;

    private static final Logger LOGGER = LoggerFactory.getLogger(MentorServiceImpl.class);

    @Override
    public MentorDto loginMentor(String emailId, String password) {
        Optional<Mentor> mentor = Optional.ofNullable(mentorRepository.findByEmailId(emailId));
        if(mentor.isEmpty()) {
            LOGGER.info("THROWING EXCEPTION");
            throw new MentorDoesNotExistException(emailId);
        }
        if(emailId.equals(mentor.get().getEmailId()) && password.equals(mentor.get().getPassword()))
            return MentorMapper.mapToMentorDto(mentor.get());
        else
            throw new InvalidCredentialsException(emailId);
    }

    @Override
    public MentorDto registerMentor(Mentor mentor) {
        List<MentorDto> mentorsList = getAllMentors();
        Optional<MentorDto> exists = mentorsList.stream()
                .filter((m)->m.getEmailId().equals(mentor.getEmailId()))
                .findFirst();
        if(exists.isPresent())
            throw new EmailAlreadyExistsException(mentor.getEmailId());
        Mentor savedMentor = mentorRepository.save(mentor);
        MentorDto savedMentorDto = MentorMapper.mapToMentorDto(savedMentor);
        return savedMentorDto;
    }

    @Override
    public List<MentorDto> getAllMentors() {
        return mentorRepository.findAll().stream().map((mentor)->MentorMapper.mapToMentorDto(mentor))
                .collect(Collectors.toList());
    }

    @Override
    public MentorDto getMentorById(int mentorId) {
        Optional<Mentor> mentor = mentorRepository.findById(mentorId);
        if(mentor.isEmpty())
            return null;
        return MentorMapper.mapToMentorDto(mentor.get());
    }

    @Override
    public void deleteMentorById(int mentorId) {
        Mentor mentor = mentorRepository.findById(mentorId).orElseThrow(
                ()-> new MentorDoesNotExistException(mentorId)
        );
    }

    @Override
    public MentorDto updateMentorById(MentorDto mentor) {
        Mentor existingMentor = mentorRepository.findById(mentor.getId()).orElseThrow(
                ()-> new MentorDoesNotExistException(mentor.getEmailId())
        );
        existingMentor.setEmailId(mentor.getEmailId());
        existingMentor.setName(mentor.getName());
        mentorRepository.save(existingMentor);
        return MentorMapper.mapToMentorDto(existingMentor);
    }

//    @Override
//    public boolean isMentorAvailable(int mentorId) {
//        Optional<Mentor> mentor = mentorRepository.findById(mentorId);
//        if(mentor.isEmpty())
//            throw new MentorDoesNotExistException(mentorId);
//        if(mentor.get().getHoursAvailable()>0)
//            return true;
//        return false;
//    }

    @Override
    public List<MentorDto> getAllAvailableMentors() {
        List<Mentor> mentors = mentorRepository.findAll();
        List<Mentor> availableMentors = mentors.stream()
                .filter((mentor)-> mentor.getHoursAvailable()>0)
                .collect(Collectors.toList());
        List<MentorDto> availableMentorDtos = availableMentors.stream()
                .map((mentor)->MentorMapper.mapToMentorDto(mentor))
                .collect(Collectors.toList());
        return availableMentorDtos;
    }

    @Override
    public MentorDetailsDto getCompleteMentorDetailsWithTrainingsAndUsers(int mentorId) {
        Mentor existingMentor = mentorRepository.findById(mentorId).orElseThrow(
                ()-> new MentorDoesNotExistException(mentorId)
        );
        List<TrainingDto> trainingDtoList = trainingFeignClient.getTrainingListByMentorId(mentorId);
        List<TrainingDetailsDto> trainingDetailsDtos = trainingDtoList.stream()
                .map((training)-> trainingFeignClient.getCompleteTrainingDetailsAlongWithAssociatedUsers(training.getTrainingId()))
                .collect(Collectors.toList());
        List<TrainingUserDto> trainingUserDtos = trainingDetailsDtos.stream()
                .map((training)->new TrainingUserDto(training.getTrainingDto(),training.getUserDtos()))
                .collect(Collectors.toList());
        return new MentorDetailsDto(MentorMapper.mapToMentorDto(existingMentor),trainingUserDtos);
    }
}
