package app.domain.repository;

import app.domain.model.Professional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProfessionalRepository extends JpaRepository<Professional, Long> {
}
