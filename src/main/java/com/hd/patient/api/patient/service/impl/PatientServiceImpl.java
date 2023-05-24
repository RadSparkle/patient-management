package com.hd.patient.api.patient.service.impl;


import com.hd.patient.api.hospital.entity.HospitalEntity;
import com.hd.patient.api.hospital.repository.HospitalRepository;
import com.hd.patient.api.patient.entity.PatientEntity;
import com.hd.patient.api.patient.model.PatientVo;
import com.hd.patient.api.patient.repository.PatientRepository;
import com.hd.patient.api.patient.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Override
    public Object addPatient(PatientVo patient) {
        //환자등록번호 생성
        String patiendRegNum = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);

        //FK(hospitalId) 확인
        Optional<HospitalEntity> hostpitalId = hospitalRepository.findById(patient.getHospitalId());
        if (!hostpitalId.isPresent()) {
            return null;
        }

        PatientEntity patientInfo = new PatientEntity(patient);
        HospitalEntity hospitalInfo = new HospitalEntity();

        patientInfo.setPatientRegNum(patiendRegNum);
        hospitalInfo.setHospitalId(patient.getHospitalId());
        patientInfo.setHospitalId(hospitalInfo);

        PatientEntity result = patientRepository.save(patientInfo);
        return result;

    }

    @Override
    public void deletePatient(PatientVo patient) {
        patientRepository.deleteById(patient.getPatientId());
    }
}
