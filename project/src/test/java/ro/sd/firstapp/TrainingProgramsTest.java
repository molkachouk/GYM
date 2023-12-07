package ro.sd.firstapp;


import ro.sd.firstapp.model.TrainingPrograms;
import ro.sd.firstapp.model.Gym;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import ro.sd.firstapp.model.dto.ProgramDTO;
import ro.sd.firstapp.repository.ProgramRepository;
import ro.sd.firstapp.service.ProgramService;
import ro.sd.firstapp.service.GymService;

import static org.mockito.AdditionalAnswers.returnsFirstArg;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class TrainingProgramsTest {
    @Mock
    private ProgramRepository programRepository;

    @Mock
    private GymService gymService;

    @InjectMocks
    private ProgramService programService;

    private final TestObject testObject = new TestObject();

    @Test
    public void testSave() {
        TrainingPrograms trainingPrograms = testObject.createTestFood();
        ProgramDTO programDTO = testObject.createTestFoodDTO();

        Mockito.when(gymService.findByName(Mockito.anyString())).thenReturn(trainingPrograms.getGym());
        Mockito.when(programRepository.findByNameAndGym(Mockito.anyString(), Mockito.any(Gym.class)))
                .thenReturn(Optional.empty());

        Mockito.when(programRepository.save(Mockito.any(TrainingPrograms.class))).then(returnsFirstArg());

        Assertions.assertDoesNotThrow(() -> programService.save(programDTO));
    }

    @Test
    public void testSaveNoRestaurant() {
        ProgramDTO programDTO = testObject.createTestFoodDTO();

        Mockito.when(gymService.findByName(Mockito.anyString())).thenReturn(null);

        Assertions.assertThrows(Exception.class, () -> programService.save(programDTO));
    }

    @Test
    public void testSaveDuplicateFood() {
        TrainingPrograms trainingPrograms = testObject.createTestFood();
        ProgramDTO programDTO = testObject.createTestFoodDTO();

        Mockito.when(gymService.findByName(Mockito.anyString())).thenReturn(trainingPrograms.getGym());
        Mockito.when(programRepository.findByNameAndGym(Mockito.anyString(), Mockito.any(Gym.class)))
                .thenReturn(Optional.of(trainingPrograms));

        Assertions.assertThrows(Exception.class, () -> programService.save(programDTO));
    }

}

