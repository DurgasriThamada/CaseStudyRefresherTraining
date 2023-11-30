package casestudy.mentortraining.trainingservice.controller;

import casestudy.mentortraining.trainingservice.dto.TrainingDetailsDto;
import casestudy.mentortraining.trainingservice.dto.TrainingDto;
import casestudy.mentortraining.trainingservice.entity.Training;
import casestudy.mentortraining.trainingservice.repository.TrainingRepository;
import casestudy.mentortraining.trainingservice.service.TrainingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.ws.rs.Path;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/trainings")
@AllArgsConstructor
public class TrainingController {
    private TrainingService trainingService;

    @Operation(
            summary = "get training list by mentor id",
            description = "get training details of the given mentor id from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 success"
    )
    @GetMapping("/getTrainingByMentorId/{id}")
    public List<TrainingDto> getTrainingListByMentorId(@PathVariable("id") int mentorId){
        return trainingService.getTrainingListByMentorId(mentorId);
    }

    @Operation(
            summary = "checking for the availability of mentor",
            description = "check if any of the training of the mentor are available today"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 success"
    )
    @GetMapping("/isMentorAvailable/{id}")
    public List<TrainingDto> isMentorAvailableToday(@PathVariable("id") int mentorId){
        return trainingService.isMentorAvailableToday(mentorId);
    }

    @Operation(
            summary = "create training data for a mentor",
            description = "store training details for a mentor in database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 success"
    )
    @PostMapping("/createTraining")
    public TrainingDto createTraining(@RequestBody TrainingDto trainingDto){
        return trainingService.createTraining(trainingDto);
    }

    @Operation(
            summary = "get all trainings data",
            description = "get all the training details from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 success"
    )
    @GetMapping
    public List<TrainingDto> getAllTrainings(){
        return trainingService.getAllTraining();
    }

    @GetMapping("/{id}")
    public TrainingDto getTrainingById(@PathVariable int id){
        return trainingService.getByTrainingId(id);
    }

    @PutMapping("/updateTraining")
    public TrainingDto updateTraining(@RequestBody Training training){
        return trainingService.updateTraining(training);
    }

    @DeleteMapping("/deleteTraining/{id}")
    public void deleteByTrainingId(@PathVariable int id){
        trainingService.deleteTraining(id);
    }

    @GetMapping("/getTrainingDetails/{id}")
    public TrainingDetailsDto getCompleteTrainingDetailsAlongWithAssociatedUsers(@PathVariable int trainingId){
        return trainingService.getCompleteTrainingDetailsAlongWithAssociatedUsers(trainingId);
    }
}
