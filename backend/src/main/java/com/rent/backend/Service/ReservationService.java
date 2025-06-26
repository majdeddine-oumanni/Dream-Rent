package com.rent.backend.Service;

import com.rent.backend.DTO.ReservationDTO;
import com.rent.backend.Mappers.ReservationMapper;
import com.rent.backend.Model.Reservation;
import com.rent.backend.Repositories.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    private final ReservationMapper mapper;
    private final ReservationRepository repository;

    public ReservationService(ReservationMapper mapper, ReservationRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public ReservationDTO create(ReservationDTO dto){
        Reservation reservation = mapper.toEntity(dto);
        Reservation savedReservation = repository.save(reservation);
        return mapper.toDTO(savedReservation);
    }

    public ReservationDTO update(Long id, ReservationDTO dto){
        Reservation reservation = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("reservation not found"));
        reservation.setStartDate(dto.getStartDate());
        reservation.setEndDate(dto.getEndDate());
        reservation.setReservationStatus(dto.getReservationStatus());
        Reservation savedReservation = repository.save(reservation);
        return mapper.toDTO(savedReservation);
    }

    public List<ReservationDTO> getAllReservations(){
        List<Reservation> reservations = repository.findAll();
        return mapper.toDTOs(reservations);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
