package com.rent.backend.Service;

import com.rent.backend.DTO.ReservationDTO;
import com.rent.backend.Mappers.ReservationMapper;
import com.rent.backend.Model.*;
import com.rent.backend.Repositories.PropertyRepository;
import com.rent.backend.Repositories.ReservationRepository;
import com.rent.backend.Repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    private final ReservationMapper mapper;
    private final ReservationRepository repository;
    private final UserRepository userRepository;
    private final PropertyRepository propertyRepository;

    public ReservationService(ReservationMapper mapper, ReservationRepository repository, UserRepository userRepository, PropertyRepository propertyRepository) {
        this.mapper = mapper;
        this.repository = repository;
        this.userRepository = userRepository;
        this.propertyRepository = propertyRepository;
    }

    public ReservationDTO create(ReservationDTO dto){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Property property = propertyRepository.findById(dto.getProperty_id())
                .orElseThrow(()-> new RuntimeException("property not found"));
        Tenant tenant = (Tenant) userRepository.findByEmail(email);
        if(tenant == null){
            throw new RuntimeException("user not found");
        }
        Reservation reservation = mapper.toEntity(dto);
        if (dto.getReservationStatus() == null){
            reservation.setReservationStatus(ReservationStatus.PENDING);
        }
        reservation.setTenant(tenant);
        reservation.setProperty(property);
        Reservation savedReservation = repository.save(reservation);
        return mapper.toDTO(savedReservation);
    }


    public ReservationDTO changeStatus(Long id, ReservationStatus status){
        Reservation reservation = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("reservation not found"));
        reservation.setReservationStatus(status);
        return mapper.toDTO(repository.save(reservation));
    }

    public List<ReservationDTO> getAllPropertyReservations(Long id){
        List<Reservation> reservations = repository.findAllByProperty_Id(id);
        return mapper.toDTOs(reservations);
    }


    public Long getReservationsTotal(Long owner_id){
        return repository.getReservationsNumberByOwnerId(owner_id);
    }

    public Long getReservationsNumberOfProperty(Long property_id){
        return repository.getReservationsNumberOfProperty(property_id);
    }

}
