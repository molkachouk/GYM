package ro.sd.firstapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "gym")
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Gym {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_gym", updatable = false, unique = true, nullable = false)
    private Integer idRestaurant;

    @NonNull
    @Column(name = "name", length = 100, unique = true)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @NonNull
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "delivery_zone",
            joinColumns = @JoinColumn(name = "id_gym"),
            inverseJoinColumns = @JoinColumn(name = "id_zone"))
    private Set<Zone> deliveryZones;

    @NonNull
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_admin")
    @JsonIgnore
    @ToString.Exclude
    private Admin admin;

    @NonNull
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_trainer")
    @JsonIgnore
    @ToString.Exclude
    private Trainer trainer;

    @JsonIgnore
    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL)
    private Set<TrainingPrograms> trainingPrograms;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
