package dev.iury.project.Exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RiqueredObjectsNullException extends RuntimeException{


    public RiqueredObjectsNullException() {
        super("Required objects are null and is not possible to continue.");
    }

    public RiqueredObjectsNullException(String s) {
       super(s);
    }

}
