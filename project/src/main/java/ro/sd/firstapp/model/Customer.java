package ro.sd.firstapp.model;


import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "customer")
public class Customer extends UserData {

    @Column(nullable = false)
    private String address;

    @Column
    private String membershipExpiration;

    @Builder(builderMethodName = "CustomerBuilder")
    public Customer(@NonNull String firstName, @NonNull String lastName, @NonNull String username, @NonNull String address, @NonNull String password, String membershipExpiration) {
        super(firstName, lastName, username, password);
        this.address = address;
        this.membershipExpiration = membershipExpiration;
    }
}

