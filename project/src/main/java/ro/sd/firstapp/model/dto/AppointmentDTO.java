package ro.sd.firstapp.model.dto;

import lombok.*;
import ro.sd.firstapp.model.ProgramCategory;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AppointmentDTO {
    @NonNull
    private String name;
    @NonNull
    private String date;
}
