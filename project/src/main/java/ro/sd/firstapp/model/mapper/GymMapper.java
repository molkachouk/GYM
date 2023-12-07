package ro.sd.firstapp.model.mapper;

import ro.sd.firstapp.model.Gym;
import ro.sd.firstapp.model.dto.GymDTO;

import java.util.stream.Collectors;

public class GymMapper implements Mapper<Gym, GymDTO> {

    private static GymMapper gymMapper = null;

    private GymMapper() { }

    public static GymMapper getInstance() {
        if (gymMapper == null) {
            gymMapper = new GymMapper();
        }
        return gymMapper;
    }

    @Override
    public Gym convertFromDTO(GymDTO gymDTO) {
        return null;
    }

    @Override
    public GymDTO convertToDTO(Gym gym) {
        return GymDTO.builder()
                .name(gym.getName())
                .address(gym.getAddress())
                .deliveryZones(gym.getDeliveryZones().stream()
                        .map(ZoneMapper.getInstance()::convertToDTO)
                        .collect(Collectors.toSet()))
                .adminDTO(AdminMapper.getInstance().convertToDTO(gym.getAdmin()))
                //.trainerDTO(TrainerMapper.getInstance().convertToDTO(gym.getTrainer()))
                .build();
    }
}
