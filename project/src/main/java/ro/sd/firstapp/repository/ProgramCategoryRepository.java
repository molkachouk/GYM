package ro.sd.firstapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.sd.firstapp.model.ProgramCategory;

public interface ProgramCategoryRepository extends JpaRepository<ProgramCategory, Integer> {
}
