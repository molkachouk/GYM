package ro.sd.firstapp.repository;

import org.springframework.stereotype.Repository;
import ro.sd.firstapp.model.TrainingPrograms;
import ro.sd.firstapp.model.Gym;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProgramRepository extends JpaRepository<TrainingPrograms, Integer>{
    Optional<TrainingPrograms> findByNameAndGym(String name, Gym gym);

    List<TrainingPrograms> findByGym(Gym gym);

    List<TrainingPrograms> findAll();
}
