package dev.iury.project.Exceptions;
import java.io.Serializable;
import java.util.Date;

public class ExceptionResponse implements Serializable {
    private Date timestamp;
    private String massage;
    private String details;

    public ExceptionResponse(Date timestamp, String massage, String details) {
        this.timestamp = timestamp;
        this.massage = massage;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
