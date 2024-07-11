package app.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity(name = "TB_SCHEDULING")
public class Scheduling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private User client;

    @OneToOne(cascade = CascadeType.ALL)
    private Professional professional;

    @OneToOne(cascade = CascadeType.ALL)
    private OfferedServices offeredServices;

    private Date availableDate;

}
