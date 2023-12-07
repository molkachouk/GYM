package ro.sd.firstapp.model.dto;

import lombok.*;
import ro.sd.firstapp.model.Gym;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper=true)
public class AdminDTO extends UserDataDTO {
    private Gym gym;

    @Builder(builderMethodName = "AdminDTOBuilder")
    public AdminDTO(@NonNull String firstName, @NonNull String lastName, @NonNull String username, @NonNull String password, Gym gym) {
        super(firstName, lastName, username, password);
        this.gym = gym;
    }
}
