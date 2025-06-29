package com.rent.backend.Controller;

import com.rent.backend.DTO.ReservationDTO;
import com.rent.backend.Service.ReservationService;
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

    @PostMapping("/post")
    public ReservationDTO addReservation(@RequestBody ReservationDTO dto){
        return service.create(dto);
    }

    @PutMapping("update/{id}")
    public ReservationDTO updateReservation(@PathVariable Long id, @RequestBody ReservationDTO dto){
        return service.update(id, dto);
    }

    @GetMapping("/get")
    public List<ReservationDTO> getReservations(){
        return service.getAllReservations();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteReservation(@PathVariable Long id){
        service.delete(id);
    }
}
