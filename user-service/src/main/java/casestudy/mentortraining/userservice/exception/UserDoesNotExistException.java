package casestudy.mentortraining.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class UserDoesNotExistException extends RuntimeException {
    private String email;
    private int id;
    public UserDoesNotExistException(String email) {
        super(String.format("Mentor doesn't exist with %s. Please register first..!!",email));
        this.email = email;
    }
    public UserDoesNotExistException(int id){
        super(String.format("Mentor doesn't exist with %d. Please register first..!!",id));
        this.id=id;
    }
}
