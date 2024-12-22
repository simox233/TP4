package emsi.sid.tp4.web;

import emsi.sid.tp4.entities.Patient;
import emsi.sid.tp4.repositories.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class PatientRestController {
    private PatientRepository patientRepository;

    @GetMapping("/patients")
    public List<Patient> patientList() {
        return patientRepository.findAll();
    }

    @GetMapping("/patients/{id}")
    public Patient patientById(@PathVariable Long id) {
        return patientRepository.findById(id).get();
    }

    @PostMapping("/patients")
    public Patient patientById(@RequestBody Patient patient) {
        patientRepository.save(patient);
        return patient;
    }
}

