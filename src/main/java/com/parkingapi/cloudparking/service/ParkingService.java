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
        var id1 = getUUID();

        Parking parking = new Parking(id, "DMS-1111", "SC", "CELTA", "PRETO");
        Parking parking1 = new Parking(id1, "DMS-2222", "SP", "VW GOL", "VERMELHO");
        parkingMap.put(id, parking);
        parkingMap.put(id1, parking1);

    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public List<Parking> findAll(){
        return new ArrayList<>(parkingMap.values());
    }

    public Parking findById(String id) {
        return parkingMap.get(id);
    }
}
