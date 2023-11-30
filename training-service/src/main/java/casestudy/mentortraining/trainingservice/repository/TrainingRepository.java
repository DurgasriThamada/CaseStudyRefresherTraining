package casestudy.mentortraining.trainingservice.repository;

import casestudy.mentortraining.trainingservice.entity.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainingRepository extends JpaRepository<Training, Integer> {
    public List<Training> findByMentorId(int mentorId);
}
