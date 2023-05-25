package com.hd.patient.api;

import com.hd.patient.api.hospital.entity.HospitalEntity;
import com.hd.patient.api.patient.entity.PatientEntity;
import com.hd.patient.api.patient.model.PatientVo;
import com.hd.patient.api.patient.repository.PatientRepository;
import com.hd.patient.api.search.model.Paging;
import com.hd.patient.api.search.repository.SearchRepository;
import com.hd.patient.api.visit.entity.VisitEntity;
import com.hd.patient.api.visit.repository.VisitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class visitRepositoryTest {
    @Autowired
    private EntityManager em;

    @Autowired
    private VisitRepository visitRepository;

    @BeforeEach
    void data_insert() {
        HospitalEntity hospital = new HospitalEntity("테스트 병원", "1234", "박회장");
        em.persist(hospital);
        PatientEntity patient1 = new PatientEntity("홍길동", "ad22sdf3r", "M", "19901115", "010-1234-1234", hospital);
        PatientEntity patient2 = new PatientEntity("환자2", "b33bfd44g", "F", "19901215", "010-3232-1111", hospital);
        PatientEntity patient3 = new PatientEntity("환자3", "hht3sdf2d", "M", "19941215", "010-2356-6666", hospital);

        em.persist(patient1);
        em.persist(patient2);
        em.persist(patient3);

        em.flush();
        em.clear();
    }

    @Test
    @DisplayName("환자 방문 입력")
    public void visit() {
        HospitalEntity hospital = em.find(HospitalEntity.class, 1L);
        PatientEntity patient = em.find(PatientEntity.class, 1L);
        VisitEntity visitEntity = new VisitEntity(hospital, patient, "20230505", "1");
        visitRepository.save(visitEntity);
    }
}