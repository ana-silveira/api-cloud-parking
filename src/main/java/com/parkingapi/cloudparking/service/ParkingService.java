package com.parkingapi.cloudparking.service;

import com.parkingapi.cloudparking.exception.ParkingNotFoundException;
import com.parkingapi.cloudparking.model.Parking;
import com.parkingapi.cloudparking.repository.ParkingRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;


//Essa classe fará injeção de dependência através do construtor.

@Service
public class ParkingService {

    /*private static Map<String, Parking> parkingMap = new HashMap<>();

    static {
        var id = getUUID();
        var id1 = getUUID();

        //Parking parking = new Parking(id, "DMS-1111", "SC", "CELTA", "PRETO");
        //parkingMap.put(id, parking);

    }*/

    private final ParkingRepository parkingRepository;

    public ParkingService(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }


    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    @Transactional
    public List<Parking> findAll() {
        return parkingRepository.findAll();
        }

    public Parking findById(String id) {
        return parkingRepository.findById(id).orElseThrow(() ->
        new ParkingNotFoundException(id));
        }

    public Parking create(Parking parkingCreate) {
        //O que o Spring faz com o @Transactional?
        //try
            // open connection
            // open transaction.begin:
                String uuid = getUUID();
                parkingCreate.setId(uuid);
                parkingCreate.setEntryDate(LocalDateTime.now());
                parkingRepository.save(parkingCreate);
            //transaction.commit
        //catch
            //transaction.rollback
        return parkingCreate;
    }

    public Parking update(String id, Parking parkingCreate){
        Parking parking = findById(id);
        parking.setColor(parkingCreate.getColor());
        parking.setState(parkingCreate.getState());
        parking.setModel(parkingCreate.getModel());
        parking.setLicense(parkingCreate.getLicense());
        parkingRepository.save(parking);
        return parking;
    }

    public void delete(String id) {
        findById(id);
        parkingRepository.deleteById(id);
    }

    public Parking checkOut(String id) {
        Parking parking = findById(id);                         //Localizar o veículo
        parking.setExitDate(LocalDateTime.now());               // Atualizar data de saída
        parking.setBill(ParkingCheckOut.getBill(parking));      // Calcular o valor
        parkingRepository.save(parking);
        return parking;                                         // retornar valor a ser pago
    }

}
