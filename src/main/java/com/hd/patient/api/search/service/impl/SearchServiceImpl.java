package com.hd.patient.api.search.service.impl;

import com.hd.patient.api.patient.entity.PatientEntity;
import com.hd.patient.api.patient.model.PatientVo;
import com.hd.patient.api.patient.repository.PatientRepository;
import com.hd.patient.api.search.repository.SearchRepository;
import com.hd.patient.api.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchRepository searchRepository;

    @Override
    public List<PatientVo> searchAll() {
        List<PatientVo> patiendList = searchRepository.searchAll();
        return patiendList;
    }

    @Override
    public List<PatientVo> searchAllByType(String keyword, String type) {
        List<PatientVo> patiendList = searchRepository.searchAllByType(keyword, type);
        return patiendList;
    }
}
