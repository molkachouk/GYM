package ro.sd.firstapp.model.dto;

import lombok.*;
import ro.sd.firstapp.model.Gym;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper=true)
public class TrainerDTO extends UserDataDTO{

    private Gym gym;
    private String experience;

    @Builder(builderMethodName = "TrainerDTOBuilder")
    public TrainerDTO(@NonNull String firstName, @NonNull String lastName, @NonNull String username, @NonNull String password, Gym gym, String experience) {
        super(firstName, lastName, username, password);
        this.gym = gym;
        this.experience = experience;
    }
}
