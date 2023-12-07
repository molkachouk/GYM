package ro.sd.firstapp.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ro.sd.firstapp.model.Appointment;
import ro.sd.firstapp.model.Gym;
import ro.sd.firstapp.model.TrainingPrograms;
import ro.sd.firstapp.model.dto.AppointmentDTO;
import ro.sd.firstapp.model.dto.ProgramDTO;
import ro.sd.firstapp.model.mapper.AppointmentMapper;
import ro.sd.firstapp.model.mapper.ProgramMapper;
import ro.sd.firstapp.repository.AppointmentRepository;
import ro.sd.firstapp.repository.ProgramRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    private final static Logger logger = LoggerFactory.getLogger(Appointment.class.getName());


    /**
     * Retrieves all program items.
     *
     * @return A List containing all the retrieved programs
     */
    public List<AppointmentDTO> findAll() {
        Optional<List<Appointment>> appointments = Optional.ofNullable(appointmentRepository.findAll());
        logger.info("Find all appointments");

        return appointments.map(appointmentList -> appointmentList.stream()
                .map(AppointmentMapper.getInstance()::convertToDTO)
                .collect(Collectors.toList())).orElseGet(ArrayList::new);
    }


    /**
     * Saves a program item to the database.
     *
     * @param appointmentDTO containing the details of the program
     * @return The saved instance of the program item.
     */
    public AppointmentDTO save(AppointmentDTO appointmentDTO) {
        Appointment appointment = Appointment.builder()
                .name(appointmentDTO.getName())
                .date(appointmentDTO.getDate())
                .build();

        return AppointmentMapper.getInstance().convertToDTO(appointmentRepository.save(appointment));
    }

}
