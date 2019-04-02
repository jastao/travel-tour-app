package jt.portfoilo.tourservice.web.rest.exceptions;

public class ApiError {

    private String message;

    private String description;

    public ApiError() {}

    public ApiError(String message, String description) {
        this.message = message;
        this.description = description;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
