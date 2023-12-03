package casestudy.mentortraining.adminservice.service;

import casestudy.mentortraining.adminservice.dto.UserDto;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(url = "http://localhost:8082", value = "USER-SERVICE")
public interface UserFeignClient {
    @PutMapping("api/users/updateUser")
    public UserDto updateUser(@Valid @RequestBody UserDto userDto);
}
