package com.rent.backend.Controller;

import com.rent.backend.DTO.ReservationDTO;
import com.rent.backend.Model.ReservationStatus;
import com.rent.backend.Service.ReservationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
@CrossOrigin("*")
public class ReservationController {
    private final ReservationService service;

    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @PreAuthorize("hasRole('TENANT')")
    @PostMapping
    public ReservationDTO addReservation(@RequestBody ReservationDTO dto){
        return service.create(dto);
    }


    @GetMapping("/{id}")
    public List<ReservationDTO> getReservations(@PathVariable Long id){
        return service.getAllPropertyReservations(id);
    }

    @PatchMapping("/updateStatus/{id}")
    public ReservationDTO changeStatus(@PathVariable Long id, @RequestParam ReservationStatus status){
        return service.changeStatus(id, status);
    }

    @PreAuthorize("hasRole('OWNER')")
    @GetMapping("/total/{owner_id}")
    public Long getTotalReservationsByOwnerId(@PathVariable Long owner_id){
        return service.getReservationsTotal(owner_id);
    }

    @PreAuthorize("hasRole('OWNER')")
    @GetMapping("/totalByProperty/{property_id}")
    public Long getReservationsNumberOfProperty(@PathVariable Long property_id){
        return service.getReservationsNumberOfProperty(property_id);
    }
}