package com.rent.backend.Service;

import com.rent.backend.DTO.ReservationDTO;
import com.rent.backend.Mappers.ReservationMapper;
import com.rent.backend.Model.Property;
import com.rent.backend.Model.Reservation;
import com.rent.backend.Model.ReservationStatus;
import com.rent.backend.Model.Tenant;
import com.rent.backend.Repositories.PropertyRepository;
import com.rent.backend.Repositories.ReservationRepository;
import com.rent.backend.Repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservationServiceTest {

@Mock
private ReservationMapper mapper;
@Mock
private ReservationRepository repository;
@Mock
private UserRepository userRepository;
@Mock
private PropertyRepository propertyRepository;
@Mock
private SecurityContext securityContext;
@Mock
private Authentication authentication;

@InjectMocks
private ReservationService service;

@BeforeEach
void setUp() {
    MockitoAnnotations.openMocks(this);
    SecurityContextHolder.setContext(securityContext);
    when(securityContext.getAuthentication()).thenReturn(authentication);
}

@Test
void testCreate() {
    ReservationDTO inputDTO = new ReservationDTO();
    inputDTO.setProperty_id(1L);
    inputDTO.setReservationStatus(null);

    Property property = new Property();
    property.setId(1L);

    Tenant tenant = new Tenant();
    tenant.setId(1L);
    tenant.setEmail("test@example.com");

    Reservation entity = new Reservation();
    entity.setProperty(property);
    entity.setTenant(tenant);

    Reservation savedEntity = new Reservation();
    savedEntity.setId(1L);
    savedEntity.setProperty(property);
    savedEntity.setTenant(tenant);
    savedEntity.setReservationStatus(ReservationStatus.PENDING);

    ReservationDTO outputDTO = new ReservationDTO();
    outputDTO.setProperty_id(1L);
    outputDTO.setReservationStatus(ReservationStatus.PENDING);

    when(authentication.getName()).thenReturn("test@example.com");
    when(propertyRepository.findById(1L)).thenReturn(Optional.of(property));
    when(userRepository.findByEmail("test@example.com")).thenReturn(tenant);
    when(mapper.toEntity(inputDTO)).thenReturn(entity);
    when(repository.save(entity)).thenReturn(savedEntity);
    when(mapper.toDTO(savedEntity)).thenReturn(outputDTO);

    ReservationDTO result = service.create(inputDTO);

    assertEquals(outputDTO.getProperty_id(), result.getProperty_id());
    assertEquals(ReservationStatus.PENDING, result.getReservationStatus());
    verify(entity).setTenant(tenant);
    verify(entity).setProperty(property);
    verify(entity).setReservationStatus(ReservationStatus.PENDING);
}

@Test
void testChangeStatus() {
    Long id = 1L;
    ReservationStatus newStatus = ReservationStatus.ACCEPTED;

    Reservation existingReservation = new Reservation();
    existingReservation.setId(id);
    existingReservation.setReservationStatus(ReservationStatus.PENDING);

    Reservation updatedReservation = new Reservation();
    updatedReservation.setId(id);
    updatedReservation.setReservationStatus(newStatus);

    ReservationDTO outputDTO = new ReservationDTO();
    outputDTO.setReservationStatus(newStatus);

    when(repository.findById(id)).thenReturn(Optional.of(existingReservation));
    when(repository.save(existingReservation)).thenReturn(updatedReservation);
    when(mapper.toDTO(updatedReservation)).thenReturn(outputDTO);

    ReservationDTO result = service.changeStatus(id, newStatus);

    assertEquals(newStatus, result.getReservationStatus());
    verify(repository).save(existingReservation);
}
}