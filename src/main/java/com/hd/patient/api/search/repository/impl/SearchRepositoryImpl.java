package com.hd.patient.api.search.repository.impl;

import com.hd.patient.api.patient.model.PatientVo;
import com.hd.patient.api.patient.model.QPatientVo;
import com.hd.patient.api.search.model.Paging;
import com.hd.patient.api.search.repository.SearchRepository;
import com.hd.patient.enums.SearchEnum;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.function.Supplier;

import static com.hd.patient.api.patient.entity.QPatientEntity.patientEntity;
import static com.hd.patient.api.visit.entity.QVisitEntity.visitEntity;
import static com.hd.patient.api.hospital.entity.QHospitalEntity.hospitalEntity;

@RequiredArgsConstructor
@Repository
public class SearchRepositoryImpl implements SearchRepository {

    private final JPAQueryFactory query;

    @Override
    public Page<PatientVo> searchAll(Paging paging) {
        List<PatientVo> patientList = query
                .select(
                        new QPatientVo(patientEntity.patientId.as("patientId"), patientEntity.patientNm, hospitalEntity.hospitalId.as("hospitalId"),
                                patientEntity.gender, patientEntity.patientRegNum, patientEntity.birth, patientEntity.phoneNum,
                                visitEntity.visitDate, visitEntity.visitStatusCode, visitEntity.visitId)
                )
                .from(patientEntity)
                .innerJoin(patientEntity.hospitalId, hospitalEntity)
                .leftJoin(patientEntity.visit, visitEntity)
                .offset(paging.getOffset())
                .limit(paging.getPageSize())
                .orderBy(visitEntity.visitDate.desc())
                .fetch();

        Long count = query
                .select(patientEntity.count())
                .from(patientEntity)
                .fetchOne();
        return new PageImpl<>(patientList, PageRequest.of(paging.getPageNum() - 1, paging.getPageSize()), count);
    }

    @Override
    public Page<PatientVo> searchAllByType(String keyword, String type, Paging paging) {
        List<PatientVo> patientList = query
                .select(
                        new QPatientVo(patientEntity.patientId.as("patientId"), patientEntity.patientNm, hospitalEntity.hospitalId.as("hospitalId"),
                                patientEntity.gender, patientEntity.patientRegNum, patientEntity.birth, patientEntity.phoneNum,
                                visitEntity.visitDate, visitEntity.visitStatusCode, visitEntity.visitId)
                )
                .from(patientEntity)
                .innerJoin(patientEntity.hospitalId, hospitalEntity)
                .leftJoin(patientEntity.visit, visitEntity)
                .offset(paging.getOffset())
                .limit(paging.getPageSize())
                .where(isSearchable(keyword, type))
                .orderBy(visitEntity.visitDate.desc())
                .fetch();

        Long count = query
                .select(patientEntity.count())
                .from(patientEntity)
                .fetchOne();

        return new PageImpl<>(patientList, PageRequest.of(paging.getPageNum() - 1, paging.getPageSize()), count);
    }

    // Null 예외처리를 위한 메소드
    BooleanBuilder nullSafeBuilder(Supplier<BooleanExpression> f) {
        try {
            return new BooleanBuilder(f.get());
        } catch (Exception e) {
            return new BooleanBuilder();
        }
    }

    // 이름, 등록번호, 생일이 동일한지 확인하는 메서드들
    BooleanBuilder nameEq(String keyword) {
        return nullSafeBuilder(() -> patientEntity.patientNm.eq(keyword));
    }
    BooleanBuilder regNumEq(String keyword) {
        return nullSafeBuilder(() -> patientEntity.patientRegNum.eq(keyword));
    }
    BooleanBuilder birthEq(String keyword) {
        return nullSafeBuilder(() -> patientEntity.birth.eq(keyword));
    }

    //각 검색조건들에따라 실행
    BooleanBuilder isSearchable(String keyword, String type) {
        if (SearchEnum.name.toString().equals(type)) {          // 이름검색
            return nameEq(keyword);
        } else if(SearchEnum.regnum.toString().equals(type)) {  //등록번호
            return regNumEq(keyword);
        } else {                                                // 생일
            return birthEq(keyword);
        }
    }
}
