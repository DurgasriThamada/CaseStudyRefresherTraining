package casestudy.mentortraining.mentorservice.controller;

import casestudy.mentortraining.mentorservice.dto.MentorDetailsDto;
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
    public MentorDto isLoginMentor(@RequestParam("email") String emailId, @RequestParam("password") String password){
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
    public MentorDto mentorRegister(@Valid @RequestBody Mentor mentor){
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

    @Operation(
            summary = "Update Mentor",
            description = "updates the mentor in database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 success"
    )
    @PutMapping("/updateMentor")
    public MentorDto updateMentor(@RequestBody MentorDto mentorDto){
        return mentorService.updateMentorById(mentorDto);
    }

    @Operation(
            summary = "Delete Mentor",
            description = "Delete mentor by id from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 success"
    )
    @DeleteMapping("/deleteMentor/{id}")
    public void deleteMentor(@PathVariable("id") int mentorId){
        mentorService.deleteMentorById(mentorId);
    }

    @Operation(
            summary = "Mentor Details",
            description = "get mentor details along with his trainings and it's associated users"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 success"
    )
    @GetMapping("/completeMentorDetails/{id}")
    public MentorDetailsDto getCompleteMentorDetailsWithTrainingsAndUsers(@PathVariable("id") int mentorId){
        return mentorService.getCompleteMentorDetailsWithTrainingsAndUsers(mentorId);
    }

    @GetMapping("/getAllAvailableMentors")
    public List<MentorDto> getAllAvailableMentors(){
        return mentorService.getAllAvailableMentors();
    }
}
