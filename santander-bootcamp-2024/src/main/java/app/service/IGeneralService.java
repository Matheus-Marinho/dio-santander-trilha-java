package app.service;

import app.domain.model.OfferedServices;
import app.domain.model.Professional;
import app.domain.model.Scheduling;
import app.domain.model.User;

import java.util.Date;
import java.util.List;

public interface IGeneralService {

    OfferedServices findOfferedServiceById(Long id);

    List<OfferedServices> findAllOfferedServices();

    OfferedServices createOfferedServices(OfferedServices newOfferedService);

    Scheduling newScheduling(Long userId, Long professionalId, Long offeredServiceId, Date schedulingDate);

    List<Scheduling> findAllSchedulings();

    Scheduling findSchedulingsById(Long id);

    List<Scheduling> findSchedulingsByUser(User user);

    List<Scheduling> findSchedulingsByProfessional(Professional professional);

    void cancelScheduling(Long id);
}
