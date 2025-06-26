package com.rent.backend.Mappers;

import com.rent.backend.DTO.ReservationDTO;
import com.rent.backend.Model.Reservation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    Reservation toEntity(ReservationDTO dto);
    ReservationDTO toDTO(Reservation reservation);
    List<ReservationDTO> toDTOs(List<Reservation> reservations);
}
