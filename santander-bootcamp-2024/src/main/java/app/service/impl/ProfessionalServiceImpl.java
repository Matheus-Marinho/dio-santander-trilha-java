package app.service.impl;

import app.domain.model.OfferedServices;
import app.domain.model.Professional;
import app.domain.repository.IProfessionalRepository;
import app.service.IGeneralService;
import app.service.IProfessionalService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProfessionalServiceImpl implements IProfessionalService {

    private final IProfessionalRepository repository;

    private IGeneralService generalService;

    public ProfessionalServiceImpl(IProfessionalRepository repository, IGeneralService generalService) {
        this.repository = repository;
        this.generalService = generalService;
    }

    @Override
    public Professional findById(Long id) {
        return repository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Professional> findAll() {
        return repository.findAll();
    }

    @Override
    public Professional createProfessional(Professional professional) {
        if(professional.getId() != null && repository.existsById(professional.getId())){
            throw new IllegalArgumentException("This Professional ID already exists.");
        }

        return repository.save(professional);
    }

    @Override
    public Professional updateProfessional(Professional professional) {
        if(professional == null || professional.getId() != null && repository.existsById(professional.getId())){
            throw new IllegalArgumentException("This professional was not found.");
        }

        return repository.save(professional);
    }

    @Override
    public void deleteProfessionalById(Long id) {
        Professional professionalToDelete = findById(id);
        repository.delete(professionalToDelete);
    }

    @Override
    public List<OfferedServices> getServicesByProfessionalId(Long id) {
        Professional professional = findById(id);
        return professional.getOfferedServicesList();
    }

    @Override
    public Professional updateServicesByProfessional(Long id, Long newOfferedServiceId) {
        Professional professional = findById(id);
        OfferedServices newService = generalService.findOfferedServiceById(newOfferedServiceId);
        if(!professional.getOfferedServicesList().contains(newService)) {
            professional.getOfferedServicesList().add(newService);
        }
        return updateProfessional(professional);
    }
}
