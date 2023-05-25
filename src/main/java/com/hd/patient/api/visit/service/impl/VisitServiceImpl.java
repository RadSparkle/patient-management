package com.hd.patient.api.visit.service.impl;

import com.hd.patient.api.hospital.entity.HospitalEntity;
import com.hd.patient.api.patient.entity.PatientEntity;
import com.hd.patient.api.visit.entity.VisitEntity;
import com.hd.patient.api.visit.model.VisitVo;
import com.hd.patient.api.visit.repository.VisitRepository;
import com.hd.patient.api.visit.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitServiceImpl implements VisitService {

    @Autowired
    private VisitRepository visitRepository;

    @Override
    public Object addVisit(VisitVo visit) {
        VisitEntity visitInfo = new VisitEntity(visit);
        HospitalEntity hospitalInfo = new HospitalEntity();
        PatientEntity patientInfo = new PatientEntity();

        hospitalInfo.setHospitalId(visit.getHospitalId());
        patientInfo.setPatientId(visit.getPatientId());

        visitInfo.setHospitalId(hospitalInfo);
        visitInfo.setPatientId(patientInfo);

        VisitEntity result = visitRepository.save(visitInfo);
        return result;
    }
}
