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
import ro.sd.firstapp.model.dto.GymDTO;
import ro.sd.firstapp.model.dto.ProgramDTO;
import ro.sd.firstapp.model.dto.TrainerDTO;
import ro.sd.firstapp.model.mapper.AdminMapper;
import ro.sd.firstapp.model.mapper.TrainerMapper;
import ro.sd.firstapp.service.*;

import java.util.List;

@RestController
@RequestMapping("/trainer")
@RequiredArgsConstructor
public class TrainerController {

    private final TrainerService TrainerService;
    private final GymService gymService;
    private final ProgramCategoryService programCategoryService;
    private final ZoneService zoneService;
    private final ProgramService programService;

    private final static Logger logger = LoggerFactory.getLogger(TrainerController.class.getName());

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TrainerDTO getCurrentTrainer(@Param("trainerUsername") String trainerUsername) {
        logger.info("Obtain trainer {} data.", trainerUsername);
        return TrainerMapper.getInstance().convertToDTO(TrainerService.findByUsername(trainerUsername));
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
    public ResponseEntity<List<ProgramDTO>> getPrograms() {
        return new ResponseEntity<>(programService.findAll(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/viewGyms")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<GymDTO>> findGyms() {
        return new ResponseEntity<>(gymService.findAll(), HttpStatus.ACCEPTED);
    }
}
