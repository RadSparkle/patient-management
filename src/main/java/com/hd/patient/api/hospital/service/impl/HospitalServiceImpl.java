package com.hd.patient.api.hospital.service.impl;

import com.hd.patient.api.hospital.entity.HospitalEntity;
import com.hd.patient.api.hospital.model.HospitalVo;
import com.hd.patient.api.hospital.repository.HospitalRepository;
import com.hd.patient.api.hospital.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    private final EntityManager em;

    @Override
    public Object addHospital(HospitalVo hospital) {
        HospitalEntity hospitalInfo = new HospitalEntity(hospital);
        HospitalEntity result = hospitalRepository.save(hospitalInfo);

        return result;
    }

    @Override
    public void deleteHospital(HospitalVo hospital) {
        hospitalRepository.deleteById(hospital.getHospitalId());
    }

    @Override
    @Transactional
    public void modifyHospital(HospitalVo hospital) {
        HospitalEntity hospitalInfo = hospitalRepository.findById(hospital.getHospitalId()).orElse(null);
        hospitalInfo.updateHospital(hospital);
        em.merge(hospitalInfo);
    }
}
