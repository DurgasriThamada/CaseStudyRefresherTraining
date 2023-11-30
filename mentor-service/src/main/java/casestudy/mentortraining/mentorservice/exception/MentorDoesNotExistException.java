package casestudy.mentortraining.mentorservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class MentorDoesNotExistException extends RuntimeException {
    private String email;
    private int id;

    public MentorDoesNotExistException(String email) {
        super(String.format("Mentor doesn't exist with %s. Please register first..!!",email));
        this.email = email;
    }

    public MentorDoesNotExistException(int id) {
        super(String.format("Mentor doesn't exist with %d. Please register first..!!",id));
        this.id = id;
    }
}
