package casestudy.mentortraining.userservice.service.impl;

import casestudy.mentortraining.userservice.dto.MentorDto;
import casestudy.mentortraining.userservice.dto.TrainingDto;
import casestudy.mentortraining.userservice.dto.UserDetailsDto;
import casestudy.mentortraining.userservice.dto.UserDto;
import casestudy.mentortraining.userservice.entity.User;
import casestudy.mentortraining.userservice.exception.EmailAlreadyExistsException;
import casestudy.mentortraining.userservice.exception.InvalidCredentialsException;
import casestudy.mentortraining.userservice.exception.UserDoesNotExistException;
import casestudy.mentortraining.userservice.mapper.UserMapper;
import casestudy.mentortraining.userservice.repository.UserRepository;
import casestudy.mentortraining.userservice.service.MentorFeignClient;
import casestudy.mentortraining.userservice.service.TrainingFeignClient;
import casestudy.mentortraining.userservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private MentorFeignClient mentorFeignClient;
    private TrainingFeignClient trainingFeignClient;
    @Override
    public boolean loginUser(String emailId, String password) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmailId(emailId));
        if(user.isEmpty()) {
            throw new UserDoesNotExistException(emailId);
        }
        if(emailId.equals(user.get().getEmailId()) && password.equals(user.get().getPassword()))
            return true;
        else
            throw new InvalidCredentialsException(emailId);
    }

    @Override
    public UserDto registerUser(User user) {
        List<UserDto> usersList = getAllUsers();
        Optional<UserDto> exists = usersList.stream()
                .filter((m)->m.getEmailId().equals(user.getEmailId()))
                .findFirst();
        if(exists.isPresent())
            throw new EmailAlreadyExistsException(user.getEmailId());
        User savedUser = userRepository.save(user);
        UserDto savedMentorDto = UserMapper.mapToUserDto(savedUser);
        return savedMentorDto;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user)->UserMapper.mapToUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(int userId) {
        User user = userRepository.findById(userId).get();
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public void deleteUserById(int userId) {
        User user = userRepository.findById(userId).orElseThrow(
                ()->new UserDoesNotExistException(userId)
        );
        userRepository.deleteById(userId);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User existingUser = userRepository.findById(userDto.getUserId()).orElseThrow(
                ()-> new UserDoesNotExistException(userDto.getEmailId())
        );
        existingUser.setEmailId(userDto.getEmailId());
        existingUser.setUserName(userDto.getUserName());
        existingUser.setTrainingId(userDto.getTrainingId());
        return UserMapper.mapToUserDto(existingUser);
    }

    @Override
    public UserDetailsDto getCompleteUserTrainingDetails(String emailId) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmailId(emailId));
        if(user.isEmpty()) {
            throw new UserDoesNotExistException(emailId);
        }
        TrainingDto trainingDto = trainingFeignClient.getTrainingById(user.get().getTrainingId());
        MentorDto mentorDto = mentorFeignClient.getMentorById(trainingDto.getMentorId());
        return new UserDetailsDto(UserMapper.mapToUserDto(user.get()),mentorDto,trainingDto);
    }

    @Override
    public List<UserDto> getAllUsersByTrainingId(int trainingId) {
        List<User> users = userRepository.findAllByTrainingId(trainingId);
        List<UserDto> userDtos = users.stream()
                .map((user)->UserMapper.mapToUserDto(user))
                .collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public List<UserDto> getAllocatedUsers() {
        List<User> users = userRepository.findAll();
        List<User> allocatedUsers = users.stream()
                .filter((user)->user.getTrainingId()>0)
                .collect(Collectors.toList());
        List<UserDto> userDtos = allocatedUsers.stream()
                .map((user)->UserMapper.mapToUserDto(user))
                .collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public List<UserDto> getNotAllocatedUsers() {
        List<User> users = userRepository.findAll();
        List<User> allocatedUsers = users.stream()
                .filter((user)->user.getTrainingId()<1)
                .collect(Collectors.toList());
        List<UserDto> userDtos = allocatedUsers.stream()
                .map((user)->UserMapper.mapToUserDto(user))
                .collect(Collectors.toList());
        return userDtos;
    }
}
