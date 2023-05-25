package com.hd.patient.api.patient.service.impl;


import com.hd.patient.api.hospital.entity.HospitalEntity;
import com.hd.patient.api.hospital.repository.HospitalRepository;
import com.hd.patient.api.patient.entity.PatientEntity;
import com.hd.patient.api.patient.model.PatientVo;
import com.hd.patient.api.patient.repository.PatientRepository;
import com.hd.patient.api.patient.service.PatientService;
import com.hd.patient.common.DefaultResponse;
import lombok.RequiredArgsConstructor;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;

    private final EntityManager em;

    @Override
    public Object addPatient(PatientVo patient) {
        //환자등록번호 생성
        String patiendRegNum = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);

        //FK(hospitalId) 확인
        HospitalEntity hospital = em.find(HospitalEntity.class, patient.getHospitalId());

        if (hospital == null) {
            return null;
        }
        PatientEntity patientInfo = new PatientEntity(patient, hospital);
        patientInfo.setPatientRegNum(patiendRegNum);
        patientInfo = patientRepository.save(patientInfo);

        return patientInfo;
    }

    @Override
    public void deletePatient(PatientVo patient) {
        patientRepository.deleteById(patient.getPatientId());
    }

    @Override
    @Transactional
    public void modifyPatient(PatientVo patient) {
        PatientEntity patientInfo = patientRepository.findById(patient.getPatientId()).orElse(null);
        patientInfo.updatePatient(patient);
        em.merge(patientInfo);
    }
}
