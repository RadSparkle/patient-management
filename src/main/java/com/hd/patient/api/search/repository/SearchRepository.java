package com.hd.patient.api.search.repository;


import com.hd.patient.api.patient.model.PatientVo;

import java.util.List;

public interface SearchRepository {
    List<PatientVo> searchAll();

    List<PatientVo> searchAllByType(String keyword, String type);
}
