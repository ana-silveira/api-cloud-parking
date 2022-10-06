package com.parkingapi.cloudparking.controller.mapper;

import com.parkingapi.cloudparking.controller.dto.ParkingDTO;
import com.parkingapi.cloudparking.model.Parking;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/*
Essa classe será responsável por fazer a conversão.

Ela também fará injeção de dependência através do construtor.
 */
@Component
public class ParkingMapper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public ParkingDTO toParkingDTO(Parking parking) {
        return MODEL_MAPPER.map(parking, ParkingDTO.class);
    }

    public List<ParkingDTO> parkingDTOList(List<Parking> parkingList) {
        return parkingList.stream().map(this::toParkingDTO).collect(Collectors.toList());
    }

}
