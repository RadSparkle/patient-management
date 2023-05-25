package com.hd.patient.api.search.service;

import com.hd.patient.api.patient.model.PatientVo;
import com.hd.patient.api.search.model.Paging;
import org.springframework.data.domain.Page;


public interface SearchService {
    Page<PatientVo> searchAll(Paging paging);

    Page<PatientVo> searchAllByType(String keyword, String type, Paging paging);
}
