package jt.portfoilo.tourservice.web.rest.exceptions;

public class TourPackageNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 58930109251931L;

    public TourPackageNotFoundException(String message) {
        super(message);
    }
}
