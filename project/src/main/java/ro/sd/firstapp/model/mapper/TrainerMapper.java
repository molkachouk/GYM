package ro.sd.firstapp.model.mapper;

import ro.sd.firstapp.model.Admin;
import ro.sd.firstapp.model.Trainer;
import ro.sd.firstapp.model.dto.AdminDTO;
import ro.sd.firstapp.model.dto.TrainerDTO;

public class TrainerMapper implements Mapper<Trainer, TrainerDTO>{

    private static TrainerMapper trainerMapper = null;

    private TrainerMapper() { }

    public static TrainerMapper getInstance() {
        if (trainerMapper == null) {
            trainerMapper = new TrainerMapper();
        }
        return trainerMapper;
    }

    @Override
    public Trainer convertFromDTO(TrainerDTO trainerDTO) {
        return null;
    }

    @Override
    public TrainerDTO convertToDTO(Trainer trainer) {
        return TrainerDTO.TrainerDTOBuilder()
                .firstName(trainer.getFirstName())
                .lastName(trainer.getLastName())
                .username(trainer.getUsername())
                .password(trainer.getPassword())
                //.gym(trainer.getGym())
                .experience("experience")
                .build();
    }
}
