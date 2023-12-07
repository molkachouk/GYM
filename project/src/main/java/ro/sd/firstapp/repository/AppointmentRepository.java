package ro.sd.firstapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.sd.firstapp.model.Appointment;
import ro.sd.firstapp.model.TrainingPrograms;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
}
