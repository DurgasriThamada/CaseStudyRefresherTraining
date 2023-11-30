package casestudy.mentortraining.trainingservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class ResourceDoesNotExistException extends RuntimeException {
    private String name;

    public ResourceDoesNotExistException(String name) {
        super(String.format("%d doesn't exist...!!",name));
        this.name = name;
    }
}
