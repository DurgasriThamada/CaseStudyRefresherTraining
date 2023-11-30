package casestudy.mentortraining.userservice.service;

import casestudy.mentortraining.userservice.dto.UserDetailsDto;
import casestudy.mentortraining.userservice.dto.UserDto;
import casestudy.mentortraining.userservice.entity.User;

import java.util.List;

public interface UserService {
    public boolean loginUser(String emailId, String password);
    public UserDto registerUser(User user);
    public List<UserDto> getAllUsers();
    public UserDto getUserById(int userId);
    public void deleteUserById(int userId);
    public UserDto updateUser(UserDto userDto);
    public UserDetailsDto getCompleteUserTrainingDetails(String email);
    public List<UserDto> getAllUsersByTrainingId(int trainingId);
}
