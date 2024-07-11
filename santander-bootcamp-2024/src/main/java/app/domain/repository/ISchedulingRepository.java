package app.domain.repository;

import app.domain.model.Scheduling;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISchedulingRepository extends JpaRepository<Scheduling, Long> {
}
