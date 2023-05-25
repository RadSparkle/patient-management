package com.hd.patient.api.hospital.service.impl;

import com.hd.patient.api.hospital.entity.HospitalEntity;
import com.hd.patient.api.hospital.model.HospitalVo;
import com.hd.patient.api.hospital.repository.HospitalRepository;
import com.hd.patient.api.hospital.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Override
    public Object addHospital(HospitalVo hospital) {
        HospitalEntity hospitalInfo = new HospitalEntity(hospital);
        HospitalEntity result = hospitalRepository.save(hospitalInfo);

        return result;
    }
}
