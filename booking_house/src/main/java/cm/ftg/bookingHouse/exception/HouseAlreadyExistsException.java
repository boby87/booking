package cm.ftg.bookingHouse.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class HouseAlreadyExistsException extends RuntimeException {

    public HouseAlreadyExistsException(String message) {
        super(message);
    }

}
