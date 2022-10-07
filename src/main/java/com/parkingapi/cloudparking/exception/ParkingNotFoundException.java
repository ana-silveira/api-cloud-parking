package com.parkingapi.cloudparking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (code = HttpStatus.NOT_FOUND)
public class ParkingNotFoundException extends RuntimeException {
//Runtime exception tem sido muito utilizada porque não exige try/catch, então se economiza muitas linhas de código.
    public ParkingNotFoundException (String id) {
        super("Parking not found with id " + id );
    }
}
