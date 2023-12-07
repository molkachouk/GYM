package ro.sd.firstapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.sd.firstapp.model.Gym;

import java.util.Optional;

@Repository
public interface GymRepository extends JpaRepository<Gym, Integer> {
    Optional<Gym> findByName(String name);

}
