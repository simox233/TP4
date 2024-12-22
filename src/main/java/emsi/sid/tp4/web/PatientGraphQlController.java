package emsi.sid.tp4.web;

import emsi.sid.tp4.dtos.PatientDto;
import emsi.sid.tp4.entities.Patient;
import emsi.sid.tp4.mapper.PatientMapper;
import emsi.sid.tp4.repositories.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientGraphQlController {

    private final PatientRepository patientRepository; // Repository pour interagir avec la base de données.
    private final PatientMapper patientMapper; // Mapper pour convertir entre DTO et entité.

    // Méthode pour récupérer tous les patients.
    @QueryMapping
    public List<Patient> allPatients() {
        return patientRepository.findAll();
    }

    // Méthode pour récupérer un patient par son ID.
    @QueryMapping
    public Patient patientById(@Argument Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Patient %s not found", id)));
    }

    // Méthode pour sauvegarder un patient (Mutation).
    @MutationMapping
    public Patient savePatient(@Argument PatientDto patientDto) {
        Patient patient = patientMapper.fromPatientDtoToPatientEntity(patientDto);
        return patientRepository.save(patient);
    }
}