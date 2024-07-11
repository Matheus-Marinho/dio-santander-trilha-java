package app.controller;

import app.domain.model.Professional;
import app.domain.model.User;
import app.service.IProfessionalService;
import app.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/professionals")
public class ProfessionalController {

    private final IProfessionalService professionalService;

    public ProfessionalController(IProfessionalService professionalService) {
        this.professionalService = professionalService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professional> findById(@PathVariable Long id){
        return ResponseEntity.ok(professionalService.findById(id));
    }

    @GetMapping("/all-professionals")
    public ResponseEntity<List<Professional>> findAll(){
        return ResponseEntity.ok(professionalService.findAll());
    }

}
