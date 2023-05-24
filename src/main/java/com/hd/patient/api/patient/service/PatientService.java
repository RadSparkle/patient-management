package com.hd.patient.api.patient.service;

import com.hd.patient.api.patient.model.PatientVo;

public interface PatientService {
    Object addPatient(PatientVo patient);

    void deletePatient(PatientVo patient);

    void modifyPatient(PatientVo patient);
}
