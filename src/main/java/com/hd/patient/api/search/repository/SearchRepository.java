package com.hd.patient.api.search.repository;


import com.hd.patient.api.patient.model.PatientVo;
import com.hd.patient.api.search.model.Paging;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SearchRepository {
    Page<PatientVo> searchAll(Paging paging);

    Page<PatientVo> searchAllByType(String keyword, String type, Paging paging);
}
