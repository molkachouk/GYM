package ro.sd.firstapp.model;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
//@AllArgsConstructor
@Table(name = "trainer")
public class Trainer extends UserData{

//    @OneToOne(mappedBy = "trainer",
//            cascade = CascadeType.ALL, orphanRemoval = true)
//    @ToString.Exclude
//    private Gym gym;

    @Builder(builderMethodName = "TrainerBuilder")
    public Trainer(@NonNull String firstName, @NonNull String lastName, @NonNull String username, @NonNull String password) {
        super(firstName, lastName, username, password);
    }
}
