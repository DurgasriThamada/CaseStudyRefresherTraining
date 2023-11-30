package casestudy.mentortraining.trainingservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "trainings")
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int trainingId;
    @Column(nullable = false)
    private String trainingName;
    @Column(nullable = false)
    private int mentorId;
    @Column(nullable = false)
    private LocalDate startingDate;
    @Column(nullable = false)
    private LocalDate endingDate;
    @Column(nullable = false)
    private int hoursPerDay;
}
