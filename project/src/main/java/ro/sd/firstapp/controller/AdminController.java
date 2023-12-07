package ro.sd.firstapp.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.sd.firstapp.model.ProgramCategory;
import ro.sd.firstapp.model.Zone;
import ro.sd.firstapp.model.dto.AdminDTO;
import ro.sd.firstapp.model.dto.CustomerDTO;
import ro.sd.firstapp.model.dto.ProgramDTO;
import ro.sd.firstapp.model.dto.GymDTO;
import ro.sd.firstapp.model.mapper.AdminMapper;
import ro.sd.firstapp.service.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService AdminService;
    private final GymService gymService;
    private final ProgramCategoryService programCategoryService;
    private final ZoneService zoneService;
    private final ProgramService programService;
    private final CustomerService customerService;

    private final static Logger logger = LoggerFactory.getLogger(AdminController.class.getName());

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AdminDTO getCurrentAdmin(@Param("adminUsername") String adminUsername) {
        logger.info("Obtain admin {} data.", adminUsername);
        return AdminMapper.getInstance().convertToDTO(AdminService.findByUsername(adminUsername));
    }

    @GetMapping("/addGym")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<Zone>> getZones() {
        try {
            return new ResponseEntity<>(zoneService.findAll(), HttpStatus.ACCEPTED);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/addGym")
    public ResponseEntity<GymDTO> addRestaurant(@RequestBody(required = false) GymDTO gymDTO) {
        logger.info("Add gym {}", gymDTO.getName());
        return new ResponseEntity<>(gymService.save(gymDTO), HttpStatus.CREATED);
    }

    @GetMapping("/addProgram")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<ProgramCategory>> getProgramCategories() {
        //System.out.println(foodCategoryService.findAll());
        return new ResponseEntity<>(programCategoryService.findAll(), HttpStatus.ACCEPTED);
    }

    @PostMapping("/addProgram")
    public ResponseEntity<ProgramDTO> addProgram(@RequestBody(required = false) ProgramDTO programDTO) {
        logger.info("Add program {}", programDTO.getName());
        return new ResponseEntity<>(programService.save(programDTO), HttpStatus.CREATED);
    }

    @GetMapping("/viewPrograms")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<ProgramDTO>> getPrograms(@Param("gymName") String gymName) {
        logger.info("View the Programs");
        return new ResponseEntity<>(programService.findByGym(gymName), HttpStatus.ACCEPTED);
    }

    @GetMapping("/viewCustomers")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<CustomerDTO>> getCustomers() {
        return new ResponseEntity<>(customerService.findAll(), HttpStatus.ACCEPTED);
    }
}
