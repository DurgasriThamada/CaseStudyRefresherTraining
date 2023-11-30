package casestudy.mentortraining.mentorservice.service.impl;

import casestudy.mentortraining.mentorservice.dto.MentorDto;
import casestudy.mentortraining.mentorservice.entity.Mentor;
import casestudy.mentortraining.mentorservice.exception.EmailAlreadyExistsException;
import casestudy.mentortraining.mentorservice.exception.InvalidCredentialsException;
import casestudy.mentortraining.mentorservice.exception.MentorDoesNotExistException;
import casestudy.mentortraining.mentorservice.mapper.MentorMapper;
import casestudy.mentortraining.mentorservice.repository.MentorRepository;
import casestudy.mentortraining.mentorservice.service.MentorService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MentorServiceImpl implements MentorService {

    private MentorRepository mentorRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(MentorServiceImpl.class);

    @Override
    public boolean loginMentor(String emailId, String password) {
        Optional<Mentor> mentor = Optional.ofNullable(mentorRepository.findByEmailId(emailId));
        if(mentor.isEmpty()) {
            LOGGER.info("THROWING EXCEPTION");
            throw new MentorDoesNotExistException(emailId);
        }
        if(emailId.equals(mentor.get().getEmailId()) && password.equals(mentor.get().getPassword()))
            return true;
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

        return MentorMapper.mapToMentorDto(existingMentor);
    }
}
