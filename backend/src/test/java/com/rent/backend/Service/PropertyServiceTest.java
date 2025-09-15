package com.rent.backend.Service;

import com.rent.backend.DTO.PropertyDTO;
import com.rent.backend.Mappers.PropertyMapper;
import com.rent.backend.Mappers.UserMapper;
import com.rent.backend.Model.Owner;
import com.rent.backend.Model.Property;
import com.rent.backend.Model.Role;
import com.rent.backend.Model.User;
import com.rent.backend.Repositories.PropertyRepository;
import com.rent.backend.Repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PropertyServiceTest {

    @Mock
    private PropertyRepository repository;
    @Mock
    private PropertyMapper mapper;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @Mock
    private SecurityContext securityContext;
    @Mock
    private Authentication authentication;

    @InjectMocks
    private PropertyService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
    }

    @Test
    void testCreate() {
        PropertyDTO inputDTO = new PropertyDTO();
        inputDTO.setCountry("USA");
        inputDTO.setCity("New York");
        inputDTO.setDescription("Cozy apartment");
        inputDTO.setAvailability(true);
        inputDTO.setRoomsNumber(2);
        inputDTO.setArea(80.5);
        inputDTO.setBathroomsNumber(1);
        inputDTO.setGuests(4);
        inputDTO.setFeatures(new HashSet<>(List.of("WiFi", "Parking")));

        Property entity = new Property();
        entity.setCountry("USA");
        entity.setCity("New York");
        entity.setDescription("Cozy apartment");

        Property savedEntity = new Property();
        savedEntity.setId(1L);
        savedEntity.setCountry("USA");
        savedEntity.setCity("New York");
        savedEntity.setDescription("Cozy apartment");

        PropertyDTO outputDTO = new PropertyDTO();
        outputDTO.setCountry("USA");
        outputDTO.setCity("New York");
        outputDTO.setDescription("Cozy apartment");

        Owner owner = new Owner();
        owner.setId(1L);
        owner.setEmail("test@example.com");

        when(authentication.getName()).thenReturn("test@example.com");
        when(userRepository.findByEmail("test@example.com")).thenReturn(owner);
        when(mapper.toEntity(inputDTO)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(savedEntity);
        when(mapper.toDTO(savedEntity)).thenReturn(outputDTO);

        PropertyDTO result = service.create(inputDTO);

        assertEquals(outputDTO.getCountry(), result.getCountry());
        assertEquals(outputDTO.getCity(), result.getCity());
        assertEquals(outputDTO.getDescription(), result.getDescription());
        verify(entity).setOwner(owner);
    }

    @Test
    void testUpdate() {
        Long id = 1L;
        PropertyDTO inputDTO = new PropertyDTO();
        inputDTO.setCountry("Canada");
        inputDTO.setCity("Toronto");
        inputDTO.setDescription("Modern condo");
        inputDTO.setAvailability(true);
        inputDTO.setRoomsNumber(3);
        inputDTO.setArea(100.0);
        inputDTO.setBathroomsNumber(2);
        inputDTO.setGuests(6);
        inputDTO.setFeatures(new HashSet<>(List.of("Pool", "Gym")));

        Owner owner = new Owner();
        owner.setId(1L);
        owner.setEmail("test@example.com");

        Property existingProperty = new Property();
        existingProperty.setId(id);
        existingProperty.setCountry("USA");
        existingProperty.setCity("New York");
        existingProperty.setOwner(owner);

        Property updatedProperty = new Property();
        updatedProperty.setId(id);
        updatedProperty.setCountry("Canada");
        updatedProperty.setCity("Toronto");
        updatedProperty.setOwner(owner);

        PropertyDTO outputDTO = new PropertyDTO();
        outputDTO.setCountry("Canada");
        outputDTO.setCity("Toronto");
        outputDTO.setDescription("Modern condo");

        when(authentication.getName()).thenReturn("test@example.com");
        when(userRepository.findByEmail("test@example.com")).thenReturn(owner);
        when(repository.findById(id)).thenReturn(Optional.of(existingProperty));
        when(repository.save(existingProperty)).thenReturn(updatedProperty);
        when(mapper.toDTO(updatedProperty)).thenReturn(outputDTO);

        PropertyDTO result = service.update(id, inputDTO);

        assertEquals(outputDTO.getCountry(), result.getCountry());
        assertEquals(outputDTO.getCity(), result.getCity());
        assertEquals(outputDTO.getDescription(), result.getDescription());
        verify(repository).save(existingProperty);
    }

    @Test
    void testGetAllProperties() {
        Property entity1 = new Property();
        entity1.setId(1L);
        entity1.setCountry("USA");
        entity1.setCity("New York");
        entity1.setDescription("Apartment");

        Property entity2 = new Property();
        entity2.setId(2L);
        entity2.setCountry("Canada");
        entity2.setCity("Toronto");
        entity2.setDescription("Condo");

        List<Property> entityList = List.of(entity1, entity2);

        PropertyDTO dto1 = new PropertyDTO();
        dto1.setCountry("USA");
        dto1.setCity("New York");
        dto1.setDescription("Apartment");

        PropertyDTO dto2 = new PropertyDTO();
        dto2.setCountry("Canada");
        dto2.setCity("Toronto");
        dto2.setDescription("Condo");

        List<PropertyDTO> expectedDtoList = List.of(dto1, dto2);

        when(repository.findAll()).thenReturn(entityList);
        when(mapper.toDTOs(entityList)).thenReturn(expectedDtoList);

        List<PropertyDTO> result = service.getAllProperties();

        assertEquals(2, result.size());
        assertEquals("USA", result.get(0).getCountry());
        assertEquals("Canada", result.get(1).getCountry());
    }

    @Test
    void testDelete() {
        Long id = 1L;
        Owner owner = new Owner();
        owner.setId(1L);
        owner.setEmail("test@example.com");

        Property property = new Property();
        property.setId(id);
        property.setOwner(owner);

        when(authentication.getName()).thenReturn("test@example.com");
        when(userRepository.findByEmail("test@example.com")).thenReturn(owner);
        when(repository.findById(id)).thenReturn(Optional.of(property));

        service.delete(id);

        verify(repository).deleteById(id);
    }

    @Test
    void testGetPropertyById() {
        Long id = 1L;
        Property entity = new Property();
        entity.setId(id);
        entity.setCountry("USA");
        entity.setCity("New York");
        entity.setDescription("Apartment");

        PropertyDTO outputDTO = new PropertyDTO();
        outputDTO.setCountry("USA");
        outputDTO.setCity("New York");
        outputDTO.setDescription("Apartment");

        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(mapper.toDTO(entity)).thenReturn(outputDTO);

        PropertyDTO result = service.getPropertyById(id);

        assertEquals(outputDTO.getCountry(), result.getCountry());
        assertEquals(outputDTO.getCity(), result.getCity());
        assertEquals(outputDTO.getDescription(), result.getDescription());
    }
}