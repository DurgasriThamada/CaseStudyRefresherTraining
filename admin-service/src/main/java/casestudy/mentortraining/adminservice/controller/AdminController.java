package casestudy.mentortraining.adminservice.controller;

import casestudy.mentortraining.adminservice.dto.MentorDetailsDto;
import casestudy.mentortraining.adminservice.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/admin")
@AllArgsConstructor
public class AdminController {
    private AdminService adminService;
    @GetMapping
    public ResponseEntity<List<MentorDetailsDto>> getCompleteMentorDetailsALongWithTrainingsAndItsUsers(){
        return ResponseEntity.ok(adminService.getCompleteMentorDetailsALongWithTrainingsAndItsUsers());
    }
}
