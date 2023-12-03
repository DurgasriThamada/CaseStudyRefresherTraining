package casestudy.mentortraining.adminservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MentorDto {
    private int id;
    private String name;
    private String emailId;
    private int hoursAvailable;
}
