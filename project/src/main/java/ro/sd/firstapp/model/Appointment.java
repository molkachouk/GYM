package ro.sd.firstapp.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_appointment", updatable = false, unique = true, nullable = false)
    private Integer idAppointment;

    @Column(nullable = false)
    private String name;

    private String date;
}
