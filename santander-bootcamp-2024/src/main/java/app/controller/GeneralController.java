package app.controller;

import app.domain.model.OfferedServices;
import app.domain.model.Scheduling;
import app.service.IGeneralService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/general")
public class GeneralController {

    private final IGeneralService generalService;

    public GeneralController(IGeneralService generalService) {
        this.generalService = generalService;
    }

    @GetMapping("/all-schedulings")
    public ResponseEntity<List<Scheduling>> findAlSchedulingsl(){
        return ResponseEntity.ok(generalService.findAllSchedulings());
    }

    @GetMapping("/all-services")
    public ResponseEntity<List<OfferedServices>> findAllServices(){
        return ResponseEntity.ok(generalService.findAllOfferedServices());
    }

}
