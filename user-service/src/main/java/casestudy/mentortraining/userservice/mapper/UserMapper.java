package casestudy.mentortraining.userservice.mapper;

import casestudy.mentortraining.userservice.dto.UserDto;
import casestudy.mentortraining.userservice.entity.User;

public class UserMapper {
    public static User mapToUser(UserDto userDto){
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setUserName(userDto.getUserName());
        user.setEmailId(userDto.getEmailId());
        user.setTrainingId(userDto.getTrainingId());
        return user;
    }

    public static UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto(
                user.getUserId(),
                user.getUserName(),
                user.getEmailId(),
                user.getTrainingId()
        );
        return userDto;
    }

}
