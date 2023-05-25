package com.hd.patient.api.hospital.service;

import com.hd.patient.api.hospital.entity.HospitalEntity;
import com.hd.patient.api.hospital.model.HospitalVo;

import java.util.List;

public interface HospitalService {
    Object addHospital(HospitalVo hospital);

    void deleteHospital(HospitalVo hospital);

    void modifyHospital(HospitalVo hospital);

    List<HospitalEntity> listHospital();
}
