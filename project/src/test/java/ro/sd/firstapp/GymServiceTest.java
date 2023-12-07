package ro.sd.firstapp;


import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import ro.sd.firstapp.model.Gym;
import ro.sd.firstapp.model.Zone;
import ro.sd.firstapp.model.mapper.GymMapper;
import ro.sd.firstapp.repository.GymRepository;
import ro.sd.firstapp.service.AdminService;
import ro.sd.firstapp.service.GymService;
import ro.sd.firstapp.service.ZoneService;

import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;

@RunWith(MockitoJUnitRunner.class)
public class GymServiceTest {
    @Mock
    private GymRepository gymRepository;

    @Mock
    private AdminService administratorService;

    @Mock
    private ZoneService zoneService;

    @InjectMocks
    private GymService gymService;

    private final TestObject testObject = new TestObject();

    @Test
    public void testSaveSuccess() {
        Gym gym = testObject.createTestRestaurant();

        Mockito.when(gymRepository.findByName(Mockito.anyString())).thenReturn(Optional.empty());
        Mockito.when(administratorService.findByUsername(Mockito.anyString())).thenReturn(gym.getAdmin());

        for (Zone zone : gym.getDeliveryZones()) {
            Mockito.when(zoneService.findByName(zone.getName())).thenReturn(zone);
        }

        Mockito.when(gymRepository.save(Mockito.any(Gym.class))).then(returnsFirstArg());

        Assertions.assertDoesNotThrow(() -> gymService.save(GymMapper.getInstance().convertToDTO(gym)));
    }

    @Test
    public void testSaveNoAdmin() {
        Gym gym = testObject.createTestRestaurant();

        Mockito.when(gymRepository.findByName(Mockito.anyString())).thenReturn(Optional.empty());
        Mockito.when(administratorService.findByUsername(Mockito.anyString())).thenReturn(null);

        Assertions.assertThrows(Exception.class, () -> gymService.save(GymMapper.getInstance().convertToDTO(gym)));
    }

    @Test
    public void testSaveDuplicateName() {
        Gym gym = testObject.createTestRestaurant();

        Mockito.when(gymRepository.findByName(Mockito.anyString())).thenReturn(Optional.of(gym));

        Assertions.assertThrows(Exception.class, () -> gymService.save(GymMapper.getInstance().convertToDTO(gym)));
    }

}
