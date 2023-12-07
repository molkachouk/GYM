package ro.sd.firstapp.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ro.sd.firstapp.model.Admin;
import ro.sd.firstapp.model.Gym;
import ro.sd.firstapp.model.Zone;
import ro.sd.firstapp.model.dto.GymDTO;
import ro.sd.firstapp.model.dto.ZoneDTO;
import ro.sd.firstapp.model.mapper.GymMapper;
import ro.sd.firstapp.repository.GymRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service used to handle the Restaurant model
 */
@Service
@RequiredArgsConstructor
public class GymService {

    private final GymRepository gymRepository;
    private final AdminService adminService;
    private final ZoneService zoneService;

    private final static Logger logger = LoggerFactory.getLogger(GymService.class.getName());


    /**
     * Retrieves a restaurant from the database by its name.
     *
     * @param name of the restaurant
     * @return The found restaurant.
     */
    public Gym findByName(String name) {
        logger.info("Get restaurant with name {}", name);
        Optional<Gym> restaurant = gymRepository.findByName(name);
        return restaurant.orElse(null);
    }

    /**
     * Saves a new restaurant to the database.
     *
     * @param gymDTO containing the details of the new restaurant
     * @return The saved restaurant instance.
     */
    public GymDTO save(GymDTO gymDTO) {
        logger.info("Save restaurant {} to database", gymDTO.getName());
        Admin admin = adminService.findByUsername(gymDTO.getAdminDTO().getUsername());

        String address = gymDTO.getAddress();

        Set<Zone> zones = new HashSet<>();
        for (ZoneDTO zoneDTO : gymDTO.getDeliveryZones()) {
            Zone zone = zoneService.findByName(zoneDTO.getName());
            if (zone != null) {
                zones.add(zone);
            }
        }

        Gym gym = Gym.builder()
                .name(gymDTO.getName())
                .address(address)
                .admin(admin)
                .deliveryZones(zones)
                .build();

        return GymMapper.getInstance().convertToDTO(gymRepository.save(gym));
    }

    /**
     * Retrieves all restaurants from the database.
     *
     * @return A List containing all restaurants.
     */
    public List<GymDTO> findAll() {
        logger.info("Find all restaurants from the database");
        return gymRepository.findAll().stream()
                .map(GymMapper.getInstance()::convertToDTO)
                .collect(Collectors.toList());
    }


}
