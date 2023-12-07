package ro.sd.firstapp.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "program_category")
public class ProgramCategory {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_category", updatable = false, unique = true, nullable = false)
    private Integer idCategory;

    @NonNull
    @Column(name = "name", length = 100)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "category", orphanRemoval = true)
    private List<TrainingPrograms> trainingPrograms;

}
