package casestudy.mentortraining.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class EmailAlreadyExistsException extends RuntimeException {

    private String name;

    public EmailAlreadyExistsException(String name) {
        super(String.format("Mentor already exists with email - %s",name));
        this.name = name;
    }
}
