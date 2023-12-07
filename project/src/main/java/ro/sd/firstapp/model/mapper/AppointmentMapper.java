package ro.sd.firstapp.model.mapper;

import ro.sd.firstapp.model.Appointment;
import ro.sd.firstapp.model.TrainingPrograms;
import ro.sd.firstapp.model.dto.AppointmentDTO;
import ro.sd.firstapp.model.dto.ProgramDTO;

public class AppointmentMapper implements Mapper<Appointment, AppointmentDTO>{

    private static AppointmentMapper appointmentMapper = null;

    private AppointmentMapper() { }

    public static AppointmentMapper getInstance() {
        if (appointmentMapper == null) {
            appointmentMapper = new AppointmentMapper();
        }
        return appointmentMapper;
    }

    @Override
    public Appointment convertFromDTO(AppointmentDTO appointmentDTO) {
        return null;
    }

    @Override
    public AppointmentDTO convertToDTO(Appointment appointment) {
        return AppointmentDTO.builder()
                .name(appointment.getName())
                .date(appointment.getDate())
                .build();
    }

}
