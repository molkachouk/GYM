package ro.sd.firstapp.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ro.sd.firstapp.model.Admin;
import ro.sd.firstapp.model.Trainer;
import ro.sd.firstapp.model.dto.CustomerDTO;
import ro.sd.firstapp.model.dto.TrainerDTO;
import ro.sd.firstapp.model.mapper.CustomerMapper;
import ro.sd.firstapp.model.mapper.TrainerMapper;
import ro.sd.firstapp.repository.AdminRepository;
import ro.sd.firstapp.repository.TrainerRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrainerService {

    private final TrainerRepository trainerRepository;
    private final static Logger logger = LoggerFactory.getLogger(TrainerService.class.getName());

    /**
     * Searches for an trainer from the database by username
     *
     * @param username of the trainer
     * @return the trainer
     */
    public Trainer findByUsername(String username) {
        logger.info("Retrieve trainer: {} from the database", username);

        Optional<Trainer> trainer = trainerRepository.findByUsername(username);

        return trainer.orElse(null);
    }

    public List<TrainerDTO> findAll() {
        logger.info("Find all customers from the database");
        return trainerRepository.findAll().stream()
                .map(TrainerMapper.getInstance()::convertToDTO)
                .collect(Collectors.toList());
    }
}
