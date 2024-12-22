package emsi.sid.tp4.web;

import emsi.sid.tp4.entities.Patient;
import emsi.sid.tp4.mapper.PatientMapper;
import emsi.sid.tp4.repositories.PatientRepository;
import emsi.sid.tp4.stub.PatientServiceGrpc;
import emsi.sid.tp4.stub.PatientServiceOuterClass;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class

PatientGrpcService extends PatientServiceGrpc.PatientServiceImplBase {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientMapper patientMapper;

    @Override
    public void getAllPatients(PatientServiceOuterClass.GetAllPatientsRequest request,
                               StreamObserver<PatientServiceOuterClass.GetAllPatientsResponse> responseObserver) {
        List<Patient> patientList = patientRepository.findAll();
        List<PatientServiceOuterClass.Patient> grpcPatients =
                patientList.stream()
                        .map(PatientMapper::fromPatientEntityToPatientProto)
                        .collect(Collectors.toList());

        PatientServiceOuterClass.GetAllPatientsResponse patientsResponse =
                PatientServiceOuterClass.GetAllPatientsResponse.newBuilder()
                        .addAllPatients(grpcPatients)
                        .build();

        responseObserver.onNext(patientsResponse);
        responseObserver.onCompleted();
    }
    @Override
    public void getPatientById(PatientServiceOuterClass.GetPatientByIdRequest request,
                               StreamObserver<PatientServiceOuterClass.GetPatientByIdResponse> responseObserver) {
        Patient patientEntity = patientRepository.findById(request.getPatientId()).get();
        PatientServiceOuterClass.GetPatientByIdResponse response =
                PatientServiceOuterClass.GetPatientByIdResponse.newBuilder()
                        .setPatient(PatientMapper.fromPatientEntityToPatientGrpc(patientEntity))
                        .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    @Override
    public void savePatient(PatientServiceOuterClass.SavePatientRequest request,
                            StreamObserver<PatientServiceOuterClass.SavePatientResponse> responseObserver) {
        Patient patientRequest = request.getPatient();
        Patient patientEntity = PatientMapper.fromPatientProtoToPatientEntity(patientRequest);
        Patient savedPatient = patientRepository.save(patientEntity);

        PatientServiceOuterClass.SavePatientResponse response =
                PatientServiceOuterClass.SavePatientResponse.newBuilder()
                        .setPatient(PatientMapper.fromPatientEntityToPatientGrpc(savedPatient))
                        .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }


}

    // Implémentez les méthodes du service gRPC ici.

