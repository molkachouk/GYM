package ro.sd.firstapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.sd.firstapp.model.ProgramCategory;
import ro.sd.firstapp.repository.ProgramCategoryRepository;

import java.util.List;

/**
 * Service used to handle the ProgramCategory model
 */
@Service
@RequiredArgsConstructor
public class ProgramCategoryService {

    private final ProgramCategoryRepository programCategoryRepository;

    /**
     * Retrieves all program categories.
     *
     * @return A list containing all retrieved program categories.
     */
    public List<ProgramCategory> findAll() {
        List<ProgramCategory> programs = programCategoryRepository.findAll();
        for (ProgramCategory programCat :
                programs) {
            System.out.println(programCat);
        }
        return programs;
    }
}