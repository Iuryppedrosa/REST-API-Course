package dev.iury.project.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MyFileNotFoundException extends RuntimeException{


    public MyFileNotFoundException(String s, Throwable cause) {
        super(s, cause);
    }

    public MyFileNotFoundException(String s) {
       super(s);
    }
}
