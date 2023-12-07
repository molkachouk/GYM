package ro.sd.firstapp.model.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class GymDTO {

    @NonNull
    private String name;

    @NonNull
    private String address;

    @NonNull
    private Set<ZoneDTO> deliveryZones;

    @NonNull
    private AdminDTO adminDTO;

//    @NonNull
//    private TrainerDTO trainerDTO;
}
