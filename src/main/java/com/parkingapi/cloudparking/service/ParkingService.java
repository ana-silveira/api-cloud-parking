package com.parkingapi.cloudparking.service;

import com.parkingapi.cloudparking.model.Parking;
import org.springframework.stereotype.Service;

import java.util.*;


//Essa classe fará injeção de dependência através do construtor.

@Service
public class ParkingService {
    private static Map<String, Parking> parkingMap = new HashMap<>();

    static {
        var id = getUUID();
        Parking parking = new Parking(id, "DMS-111", "SC", "CELTA", "PRETO");
        parkingMap.put(id, parking);
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public List<Parking> findAll(){
        return new ArrayList<>(parkingMap.values());
    }
}
