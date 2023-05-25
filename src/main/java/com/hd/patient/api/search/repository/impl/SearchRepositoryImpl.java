package com.hd.patient.api.search.repository.impl;

import com.hd.patient.api.patient.model.PatientVo;
import com.hd.patient.api.patient.model.QPatientVo;
import com.hd.patient.api.search.repository.SearchRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.hd.patient.api.patient.entity.QPatientEntity.patientEntity;
import static com.hd.patient.api.visit.entity.QVisitEntity.visitEntity;
import static com.hd.patient.api.hospital.entity.QHospitalEntity.hospitalEntity;

@RequiredArgsConstructor
@Repository
public class SearchRepositoryImpl implements SearchRepository {

    private final JPAQueryFactory query;

    @Override
    public List<PatientVo> searchAll() {
        List<PatientVo> patientList = query
                .select(
                        new QPatientVo(patientEntity.patientId.as("patientId"), patientEntity.patientNm, hospitalEntity.hospitalId.as("hospitalId"),
                                patientEntity.gender, patientEntity.patientRegNum, patientEntity.birth, patientEntity.phoneNum,
                                visitEntity.visitDate, visitEntity.visitStatusCode, visitEntity.visitId)
                )
                .from(patientEntity)
                .innerJoin(patientEntity.hospitalId, hospitalEntity)
                .leftJoin(patientEntity.visit, visitEntity)
                .orderBy(visitEntity.visitDate.desc())
                .fetch();
        return patientList;
    }
}
