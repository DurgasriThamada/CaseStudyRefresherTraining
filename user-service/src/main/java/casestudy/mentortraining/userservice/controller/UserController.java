package casestudy.mentortraining.userservice.controller;

import casestudy.mentortraining.userservice.dto.UserDetailsDto;
import casestudy.mentortraining.userservice.dto.UserDto;
import casestudy.mentortraining.userservice.entity.User;
import casestudy.mentortraining.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping("/login")
    public boolean isLoginUser(@RequestParam("email") String emailId, @RequestParam("password") String password){
        return userService.loginUser(emailId,password);
    }

    @PostMapping("/register")
    public UserDto userRegister(@RequestBody User user){
        return userService.registerUser(user);
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable("id") int userId){
        return userService.getUserById(userId);
    }

    @PutMapping("/updateUser")
    public UserDto updateUser(@RequestBody UserDto userDto){
        return userService.updateUser(userDto);
    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable("id") int userId){
        userService.deleteUserById(userId);
    }

    @GetMapping("/getTrainingDetails")
    public UserDetailsDto getCompleteUserTrainingDetails(@RequestParam("email") String email){
        return userService.getCompleteUserTrainingDetails(email);
    };
    @GetMapping("/getAllUsersByTrainingId/{id}")
    public List<UserDto> getAllUsersByTrainingId(@PathVariable("id") int trainingId){
        return userService.getAllUsersByTrainingId(trainingId);
    }

}
