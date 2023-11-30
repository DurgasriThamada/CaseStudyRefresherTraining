package casestudy.mentortraining.userservice.repository;

import casestudy.mentortraining.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByEmailId(String emailId);
    public List<User> findAllByTrainingId(int trainingId);
}
