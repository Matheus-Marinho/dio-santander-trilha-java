package app.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TB_AVAILABILITY")
public class TimeAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date availableDate;

    @OneToOne(cascade = CascadeType.ALL)
    private Professional professional;

    private Boolean isAvailable;
}
