package com.parkingapi.cloudparking.controller;

import com.parkingapi.cloudparking.controller.dto.ParkingCreateDTO;
import com.parkingapi.cloudparking.controller.dto.ParkingDTO;
import com.parkingapi.cloudparking.controller.mapper.ParkingMapper;
import com.parkingapi.cloudparking.model.Parking;
import com.parkingapi.cloudparking.service.ParkingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
@Api(tags = "ParkingController")

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
    @ApiOperation("Find all parkings")
    public ResponseEntity<List<ParkingDTO>> findAll(){
        List<Parking> parkingList =  parkingService.findAll();
        List<ParkingDTO> result;
        result = parkingMapper.parkingDTOList(parkingList);
        return ResponseEntity.ok(result);
        }
        // O Response Entity é uma boa prática (encapsula as informações da lista).
        
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

    @GetMapping ("/{id}")
    public ResponseEntity<ParkingDTO> findById(@PathVariable String id){
        Parking parking =  parkingService.findById(id);
        /* if (parking == null) { return ResponseEntity.notFound().build();}  - Funciona mas não explica o erro.*/
        //Vou tratar a minha exceção na camada de Serviço; Não foi necessário mexer no controller.
        ParkingDTO result = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO dto){
        var parkingCreate = parkingMapper.toParkingCreate(dto);
        Parking parking =  parkingService.create(parkingCreate);
        ParkingDTO result = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
