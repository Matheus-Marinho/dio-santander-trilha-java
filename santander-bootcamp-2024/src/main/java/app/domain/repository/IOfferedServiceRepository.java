package app.domain.repository;

import app.domain.model.OfferedServices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOfferedServiceRepository extends JpaRepository<OfferedServices, Long> {
}
