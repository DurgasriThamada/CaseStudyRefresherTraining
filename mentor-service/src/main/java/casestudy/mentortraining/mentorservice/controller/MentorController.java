package casestudy.mentortraining.mentorservice.controller;

import casestudy.mentortraining.mentorservice.dto.MentorDto;
import casestudy.mentortraining.mentorservice.entity.Mentor;
import casestudy.mentortraining.mentorservice.service.MentorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.ws.rs.Path;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/mentors")
@AllArgsConstructor
public class MentorController {

    private MentorService mentorService;
    private static final Logger LOGGER = LoggerFactory.getLogger(MentorController.class);

    @Operation(
            summary = "Mentor Login",
            description = "Checks if the mentor has previously registered or not from the data in database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 success"
    )
    @GetMapping("/login")
    public boolean isLoginMentor(@RequestParam("email") String emailId, @RequestParam("password") String password){
        LOGGER.info(String.format("EMAIL %s  PASSWORD %s",emailId,password));
        return mentorService.loginMentor(emailId,password);
    }

    @Operation(
            summary = "Mentor Register",
            description = "Saves the mentor data in database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 success"
    )
    @PostMapping("/register")
    public MentorDto mentorRegister(@RequestBody @Valid Mentor mentor){
        return mentorService.registerMentor(mentor);
    }

    @Operation(
            summary = "get all mentors data",
            description = "get all existing mentor details from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 success"
    )
    @GetMapping
    public List<MentorDto> getAllMentors(){
        return mentorService.getAllMentors();
    }

    @Operation(
            summary = "get mentor by id data",
            description = "get mentor details by given id from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 success"
    )
    @GetMapping("/{id}")
    public MentorDto getMentorById(@PathVariable("id") int mentorId){
        return mentorService.getMentorById(mentorId);
    }

    @PutMapping("/updateMentor")
    public MentorDto updateMentor(@RequestBody MentorDto mentorDto){
        return mentorService.updateMentorById(mentorDto);
    }

    @DeleteMapping("/deleteMentor/{id}")
    public void deleteMentor(@PathVariable("id") int mentorId){
        mentorService.deleteMentorById(mentorId);
    }

}
