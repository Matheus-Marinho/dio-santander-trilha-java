package app.domain.repository;

import app.domain.model.TimeAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITimeAvailabilityRepository extends JpaRepository<TimeAvailability, Long> {
}
