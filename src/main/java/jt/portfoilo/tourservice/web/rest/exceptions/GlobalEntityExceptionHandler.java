package jt.portfoilo.tourservice.web.rest.exceptions;

import jt.portfoilo.tourservice.web.rest.util.HeaderUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalEntityExceptionHandler {

    @ExceptionHandler(value = { TourPackageNotFoundException.class })
    public ResponseEntity<ApiError> handleSpecificException(Exception ex, WebRequest request) {
        return new ResponseEntity<ApiError>(new ApiError(ex.getMessage(), request.getDescription(false)),
                HeaderUtil.createFailureAlert(null, "specificException", ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<ApiError> handleGeneralException(Exception ex, WebRequest request) {
        return new ResponseEntity<ApiError>(new ApiError(ex.getMessage(), request.getDescription(false)),
                HeaderUtil.createFailureAlert(null, "generalException", ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR)
                ;
    }
}
