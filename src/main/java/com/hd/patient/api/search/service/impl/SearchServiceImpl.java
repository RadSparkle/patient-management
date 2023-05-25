package com.hd.patient.api.search.service.impl;

import com.hd.patient.api.patient.model.PatientVo;
import com.hd.patient.api.search.model.Paging;
import com.hd.patient.api.search.repository.SearchRepository;
import com.hd.patient.api.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchRepository searchRepository;

    @Override
    public Page<PatientVo> searchAll(Paging paging) {
        Page<PatientVo> patiendList = searchRepository.searchAll(paging);
        return patiendList;
    }

    @Override
    public Page<PatientVo> searchAllByType(String keyword, String type, Paging paging) {
        Page<PatientVo> patiendList = searchRepository.searchAllByType(keyword, type, paging);
        return patiendList;
    }
}
