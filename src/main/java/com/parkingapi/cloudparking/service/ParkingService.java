package com.parkingapi.cloudparking.service;

import com.parkingapi.cloudparking.exception.ParkingNotFoundException;
import com.parkingapi.cloudparking.model.Parking;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;


//Essa classe fará injeção de dependência através do construtor.

@Service
public class ParkingService {
    private static Map<String, Parking> parkingMap = new HashMap<>();

    static {
        var id = getUUID();
        var id1 = getUUID();

        //Parking parking = new Parking(id, "DMS-1111", "SC", "CELTA", "PRETO");
        //parkingMap.put(id, parking);

    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public List<Parking> findAll(){
        return new ArrayList<>(parkingMap.values());
    }

    public Parking findById(String id) {
        Parking parking = parkingMap.get(id);
        if (parking == null) {
            throw new ParkingNotFoundException(id);
        }
        return parking;
    }

    public Parking create(Parking parkingCreate) {
        String uuid = getUUID();
        parkingCreate.setId(uuid);

        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingMap.put(uuid, parkingCreate);
        return parkingCreate;
    }

    public Parking update(String id, Parking parkingCreate){
        Parking parking = findById(id);
        parking.setColor(parkingCreate.getColor());
        parkingMap.replace(id, parking);
        return parking;

    }

    public void delete(String id) {
        findById(id);
        parkingMap.remove(id);
    }

    /*
    public Parking exitTime(String id) {
        findById(id); // recuperar o estacionado
        // Atualizar data de saída
        // calcular o valor (pode fazer uma tabela de valores em que o tempo de cada hora está definido)
        // retornar data de saida e valor que o cliente deve ser cobrado
    }
    */
}
