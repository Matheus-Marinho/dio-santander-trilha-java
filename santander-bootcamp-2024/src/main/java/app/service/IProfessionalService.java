package app.service;

import app.domain.model.OfferedServices;
import app.domain.model.Professional;

import java.util.List;

public interface IProfessionalService {

    Professional findById(Long id);

    List<Professional> findAll();

    Professional createProfessional(Professional professional);

    Professional updateProfessional(Professional professional);

    void deleteProfessionalById(Long id);

    List<OfferedServices> getServicesByProfessionalId(Long id);

    Professional updateServicesByProfessional(Long id, Long newOfferedServiceId);
}
