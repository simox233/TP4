package emsi.sid.tp4.mapper;

import emsi.sid.tp4.dtos.PatientDto;
import emsi.sid.tp4.entities.Patient;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {
    private ModelMapper modelMapper = new ModelMapper();

    public Patient fromPatientDtoToPatientEntity(PatientDto patientDto) {
        Patient patient = modelMapper.map(patientDto, Patient.class);
        return patient;
    }
}



