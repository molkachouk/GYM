package ro.sd.firstapp.model.dto;

import lombok.*;
import ro.sd.firstapp.model.ProgramCategory;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProgramDTO {
    @NonNull
    private String name;
    @NonNull
    private Integer duration;
    @NonNull
    private String description;
    @NonNull
    private ProgramCategory category;
    @NonNull
    private GymDTO gymDTO;
}
