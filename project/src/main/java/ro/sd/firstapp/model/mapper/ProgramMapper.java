package ro.sd.firstapp.model.mapper;

import ro.sd.firstapp.model.TrainingPrograms;
import ro.sd.firstapp.model.dto.ProgramDTO;

public class ProgramMapper implements Mapper<TrainingPrograms, ProgramDTO> {

    private static ProgramMapper programMapper = null;

    private ProgramMapper() { }

    public static ProgramMapper getInstance() {
        if (programMapper == null) {
            programMapper = new ProgramMapper();
        }
        return programMapper;
    }

    @Override
    public TrainingPrograms convertFromDTO(ProgramDTO programDTO) {
        return null;
    }

    @Override
    public ProgramDTO convertToDTO(TrainingPrograms trainingPrograms) {
        return ProgramDTO.builder()
                .name(trainingPrograms.getName())
                .duration(trainingPrograms.getDuration())
                .description(trainingPrograms.getDescription())
                .category(trainingPrograms.getCategory())
                .gymDTO(GymMapper.getInstance().convertToDTO(trainingPrograms.getGym()))
                .build();
    }
}
