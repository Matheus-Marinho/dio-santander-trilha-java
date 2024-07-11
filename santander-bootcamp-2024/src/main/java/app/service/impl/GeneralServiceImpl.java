package app.service.impl;

import app.domain.model.*;
import app.domain.repository.IOfferedServiceRepository;
import app.domain.repository.IProfessionalRepository;
import app.domain.repository.ISchedulingRepository;
import app.domain.repository.ITimeAvailabilityRepository;
import app.service.IGeneralService;
import app.service.IProfessionalService;
import app.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class GeneralServiceImpl implements IGeneralService {

    private final IOfferedServiceRepository offeredServiceRepository;

    private final ISchedulingRepository schedulingRepository;

    private final ITimeAvailabilityRepository timeAvailabilityRepository;

    private IUserService userService;

    private IProfessionalRepository professionalRepository;

    public GeneralServiceImpl(IOfferedServiceRepository offeredServiceRepository,
                              ISchedulingRepository schedulingRepository,
                              ITimeAvailabilityRepository timeAvailabilityRepository,
                              IProfessionalRepository professionalRepository,
                              IUserService userService) {
        this.offeredServiceRepository = offeredServiceRepository;
        this.schedulingRepository = schedulingRepository;
        this.timeAvailabilityRepository = timeAvailabilityRepository;
        this.professionalRepository = professionalRepository;
        this.userService = userService;
    }

    @Override
    public OfferedServices findOfferedServiceById(Long id) {
        return offeredServiceRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<OfferedServices> findAllOfferedServices() {
        return offeredServiceRepository.findAll();
    }

    @Override
    public OfferedServices createOfferedServices(OfferedServices newOfferedService) {
        if(newOfferedService.getId() != null && offeredServiceRepository.existsById(newOfferedService.getId())){
            throw new IllegalArgumentException("This Professional ID already exists.");
        }

        return offeredServiceRepository.save(newOfferedService);
    }

    @Override
    public Scheduling newScheduling(Long userId, Long professionalId, Long offeredServiceId, Date schedulingDate) {
        User user = userService.findById(userId);
        Professional professional = professionalRepository.findById(professionalId).orElseThrow();
        OfferedServices offeredService = findOfferedServiceById(offeredServiceId);
        if(!professional.getOfferedServicesList().contains(offeredService)){
            throw new IllegalArgumentException("This professional does not perform this service!");
        }

        TimeAvailability availability = new TimeAvailability();
        availability.setProfessional(professional);
        availability.setAvailableDate(schedulingDate);
        availability.setIsAvailable(false);

        availability = timeAvailabilityRepository.save(availability);

        Scheduling scheduling = new Scheduling();
        scheduling.setClient(user);
        scheduling.setProfessional(professional);
        scheduling.setOfferedServices(offeredService);
        scheduling.setAvailableDate(schedulingDate);

        return schedulingRepository.save(scheduling);
    }

    @Override
    public List<Scheduling> findAllSchedulings() {
        return schedulingRepository.findAll();
    }

    @Override
    public Scheduling findSchedulingsById(Long id) {
        return schedulingRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Scheduling> findSchedulingsByUser(User user) {
        List<Scheduling> allSchedulings = findAllSchedulings();

        return allSchedulings.stream()
                .filter(scd -> scd.getClient().equals(user))
                .collect(Collectors.toList());
    }

    @Override
    public List<Scheduling> findSchedulingsByProfessional(Professional professional) {
        List<Scheduling> allSchedulings = findAllSchedulings();

        return allSchedulings.stream()
                .filter(scd -> scd.getProfessional().equals(professional))
                .collect(Collectors.toList());
    }

    @Override
    public void cancelScheduling(Long id) {
        Scheduling schedulingToDelete = findSchedulingsById(id);
        if(schedulingToDelete == null){
            throw new IllegalArgumentException("This scheduling was not found.");
        }
        schedulingRepository.delete(schedulingToDelete);
    }
}
