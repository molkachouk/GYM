package ro.sd.firstapp.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.sd.firstapp.model.dto.*;
import ro.sd.firstapp.model.mapper.CustomerMapper;
import ro.sd.firstapp.service.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {


    private final CustomerService customerService;
    private final GymService gymService;
    private final ProgramService programService;
    private final TrainerService trainerService;
    private final AppointmentService appointmentService;

    private final static Logger logger = LoggerFactory.getLogger(CustomerController.class.getName());


    @PostMapping("/register")
    public ResponseEntity<CustomerDTO> registerCustomer(@RequestBody(required = false) CustomerDTO customerDTO) {
        logger.info("Customer {} registered", customerDTO.getUsername());
        return new ResponseEntity<>(customerService.register(customerDTO), HttpStatus.CREATED);
    }

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CustomerDTO getCurrentCustomer(@Param("customerUsername") String customerUsername) {
        return CustomerMapper.getInstance().convertToDTO(customerService.findByUsername(customerUsername));
    }

    @GetMapping("/viewGyms")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<GymDTO>> findGyms() {
        return new ResponseEntity<>(gymService.findAll(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/viewPrograms")
    public ResponseEntity<List<ProgramDTO>> getPrograms() {
        return new ResponseEntity<>(programService.findAll(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/viewTrainers")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<TrainerDTO>> getTrainers() {
        return new ResponseEntity<>(trainerService.findAll(), HttpStatus.ACCEPTED);
    }

    @PostMapping("/makeAppointment")
    public ResponseEntity<AppointmentDTO> addAppointment(@RequestBody(required = false) AppointmentDTO appointmentDTO) {
        logger.info("Add appointment {}", appointmentDTO.getName());
        return new ResponseEntity<>(appointmentService.save(appointmentDTO), HttpStatus.CREATED);
    }

    @GetMapping("/viewAppointments")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<AppointmentDTO>> getAppointments() {
        return new ResponseEntity<>(appointmentService.findAll(), HttpStatus.ACCEPTED);
    }

}
