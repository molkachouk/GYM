package ro.sd.firstapp.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ro.sd.firstapp.model.TrainingPrograms;
import ro.sd.firstapp.model.Gym;
import ro.sd.firstapp.model.dto.ProgramDTO;
import ro.sd.firstapp.model.mapper.ProgramMapper;
import ro.sd.firstapp.repository.ProgramRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service used to handle the Program model
 */
@Service
@RequiredArgsConstructor
public class ProgramService {

    private final ProgramRepository programRepository;

    private final GymService gymService;

    private final static Logger logger = LoggerFactory.getLogger(ProgramService.class.getName());

    /**
     * Retrieves a program item by gym.
     *
     * @param gymName of the gym from which the program belongs to
     * @return the retrieved program
     */
    public List<ProgramDTO> findByGym(String gymName) {
        Gym gym = gymService.findByName(gymName);
        Optional<List<TrainingPrograms>> programs = Optional.ofNullable(programRepository.findByGym(gym));
        logger.info("Find programs by gyms: {}", gymName);
        return programs.map(programList -> programList.stream()
                .map(ProgramMapper.getInstance()::convertToDTO)
                .collect(Collectors.toList())).orElseGet(ArrayList::new);
    }

    /**
     * Retrieves all program items.
     *
     * @return A List containing all the retrieved programs
     */
    public List<ProgramDTO> findAll() {
        Optional<List<TrainingPrograms>> programs = Optional.ofNullable(programRepository.findAll());
        logger.info("Find all programs");

        return programs.map(programList -> programList.stream()
                .map(ProgramMapper.getInstance()::convertToDTO)
                .collect(Collectors.toList())).orElseGet(ArrayList::new);
    }


    /**
     * Saves a program item to the database.
     *
     * @param programDTO containing the details of the program
     * @return The saved instance of the program item.
     */
    public ProgramDTO save(ProgramDTO programDTO) {
        Gym gym = gymService.findByName(programDTO.getGymDTO().getName());
        logger.info("Save program {} to database", programDTO.getName());

        TrainingPrograms trainingPrograms = TrainingPrograms.builder()
                .name(programDTO.getName())
                .duration(programDTO.getDuration())
                .description(programDTO.getDescription())
                .category(programDTO.getCategory())
                .gym(gym)
                .build();

        return ProgramMapper.getInstance().convertToDTO(programRepository.save(trainingPrograms));
    }


}
