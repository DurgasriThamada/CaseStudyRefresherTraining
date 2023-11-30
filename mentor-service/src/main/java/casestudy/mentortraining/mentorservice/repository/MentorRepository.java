package casestudy.mentortraining.mentorservice.repository;

import casestudy.mentortraining.mentorservice.entity.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentorRepository extends JpaRepository<Mentor, Integer> {
    public Mentor findByEmailId(String emailId);
}
