package emsi.sid.tp4.repositories;

import emsi.sid.tp4.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}