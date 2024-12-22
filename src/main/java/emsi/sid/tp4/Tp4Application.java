package emsi.sid.tp4;

import emsi.sid.tp4.entities.Patient;
import emsi.sid.tp4.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Tp4Application {

    public static void main(String[] args) {
        SpringApplication.run(Tp4Application.class, args);
    }

    @Bean
    CommandLineRunner start(PatientRepository patientRepository){
        return args -> {
            patientRepository.save(Patient.builder().name("mohamed").email("mohamed@gmail.com").description_diagnostic("Check-us").build());
            patientRepository.save(Patient.builder().name("yassine").email("yassine@gmail.com").description_diagnostic("Scanner").build());
            patientRepository.save(Patient.builder().name("ahmed").email("ahmed@gmail.com").description_diagnostic("medical analysis").build());
            patientRepository.save(Patient.builder().name("samir").email("samir@gmail.com").description_diagnostic("Check-up").build());

        };
    }

}
