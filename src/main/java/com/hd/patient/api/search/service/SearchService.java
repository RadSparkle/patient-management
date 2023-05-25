package com.hd.patient.api.search.service;

import com.hd.patient.api.patient.model.PatientVo;

import java.util.List;

public interface SearchService {
    List<PatientVo> searchAll();

    List<PatientVo> searchAllByType(String keyword, String type);
}
