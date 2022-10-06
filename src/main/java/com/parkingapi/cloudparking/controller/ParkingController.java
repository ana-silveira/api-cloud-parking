package com.parkingapi.cloudparking.controller;

import com.parkingapi.cloudparking.controller.dto.ParkingDTO;
import com.parkingapi.cloudparking.controller.mapper.ParkingMapper;
import com.parkingapi.cloudparking.model.Parking;
import com.parkingapi.cloudparking.service.ParkingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    private final ParkingService parkingService;
    private final ParkingMapper parkingMapper;

    public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
        this.parkingService = parkingService;
        this.parkingMapper = parkingMapper;

    }
    // Ao invés do construtor, poderia usar também o @Autowired.
    // Não recomendável para versões mais antigas do JAVA.

    @GetMapping
    public List<ParkingDTO> findAll(){
        List<Parking> parkingList =  parkingService.findAll();
        List<ParkingDTO> result;
        result = parkingMapper.parkingDTOList(parkingList);
        return result;
        }
        
        /*
        var parking = new Parking();
        parking.setColor("PRETO");
        parking.setLicense("MSS-1111");
        parking.setModel("VW-GOL");
        parking.setState("SP");
        return Arrays.asList(parking);
            Comentário: Não é uma boa prática retornar direto uma lista da minha entidade de banco de dados.
            Por isso aplicamos o pattern de DTO, e ele sim vai representar a nossa view para a API.
            Nunca se expõe um objeto de domínio na API, é preciso fazer essa separação! Ver classe ParkingService.
        */
}
