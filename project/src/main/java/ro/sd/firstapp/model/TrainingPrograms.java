package ro.sd.firstapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "training_programs")
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TrainingPrograms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_program", updatable = false, unique = true, nullable = false)
    private Integer idProgram;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private int duration;

    @NonNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_category")
    private ProgramCategory category;


    @ManyToOne
    @JoinColumn(name = "id_gym", nullable = false)
    private Gym gym;


    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
