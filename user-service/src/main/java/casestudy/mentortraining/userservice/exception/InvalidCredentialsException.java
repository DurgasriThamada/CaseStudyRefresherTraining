package casestudy.mentortraining.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class InvalidCredentialsException extends RuntimeException{
    private String name;
    public InvalidCredentialsException(){
        super("Invalid Username or Password");
    }

    public InvalidCredentialsException(String name){
        super("Invalid Username or Password");
        this.name = name;
    }
}
