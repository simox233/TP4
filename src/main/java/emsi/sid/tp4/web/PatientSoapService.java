package emsi.sid.tp4.web;

import emsi.sid.tp4.dtos.PatientDto;
import emsi.sid.tp4.entities.Patient;
import emsi.sid.tp4.mapper.PatientMapper;
import emsi.sid.tp4.repositories.PatientRepository;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@WebService
public class PatientSoapService {

    private PatientRepository patientRepository;
    private PatientMapper patientMapper;

    public List<Patient> patientList() {
        return patientRepository.findAll();
    }

    @WebMethod
    public Patient patientById(@WebParam(name = "id") Long id) {
        return patientRepository.findById(id).get();
    }

    @WebMethod
    public Patient savePatient(@WebParam(name = "patient") PatientDto patientDto) {
        Patient patient = patientMapper.fromPatientDtoToPatientEntity(patientDto);
        return patientRepository.save(patient);
    }
}

