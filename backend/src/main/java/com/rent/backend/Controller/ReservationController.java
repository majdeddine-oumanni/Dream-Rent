package com.rent.backend.Controller;

import com.rent.backend.DTO.ReservationDTO;
import com.rent.backend.Service.ReservationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
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

    @DeleteMapping("/delete/{id}")
    public void deleteReservation(@PathVariable Long id){
        service.delete(id);
    }

    @GetMapping("/total/{owner_id}")
    public Long getTotalReservationsByOwnerId(@PathVariable Long owner_id){
        return service.getReservationsTotal(owner_id);
    }
}
