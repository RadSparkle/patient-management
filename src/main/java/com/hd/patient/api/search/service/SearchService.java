package com.hd.patient.api.search.service;

import com.hd.patient.api.patient.model.PatientVo;

import java.util.List;

public interface SearchService {
    List<PatientVo> searchAll();

    List<PatientVo> searchByType(String keyword, String type);
}
