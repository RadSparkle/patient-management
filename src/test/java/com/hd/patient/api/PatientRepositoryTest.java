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
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class PatientRepositoryTest {
    @Autowired
    private EntityManager em;
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private SearchRepository searchRepository;


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
    @DisplayName("환자 전체 조회")
    void selectAll() {
        Paging paging = new Paging();
        Page<PatientVo> patientList = searchRepository.searchAll(paging);
        System.out.println(patientList.getContent());
    }

    @Test
    @DisplayName("환자 조건 조회")
    void selectAllByType() {
        Paging paging1 = new Paging();
        Paging paging2 = new Paging();
        Paging paging3 = new Paging();

        String type1 = "name";
        String keyword1 = "홍길동";
        Page<PatientVo> patiendList1 = searchRepository.searchAllByType(keyword1, type1, paging1);
        System.out.println(patiendList1.getContent());

        String type2 = "birth";
        String keyword2 = "19901215";
        Page<PatientVo> patiendList2 = searchRepository.searchAllByType(keyword2, type2, paging2);
        System.out.println(patiendList2.getContent());


        String type3 = "regnum";
        String keyword3 = "hht3sdf2d";
        Page<PatientVo> patiendList3 = searchRepository.searchAllByType(keyword3, type3, paging3);
        System.out.println(patiendList3.getContent());
    }

    @Test
    @DisplayName("환자 등록")
    public void insert() {
        HospitalEntity hospital = em.find(HospitalEntity.class, 1L);
        PatientEntity newPatient = new PatientEntity(
                "뉴페이스", "998877",
                "F", "2011-03-03", "010-0002-0101", hospital
        );

        patientRepository.save(newPatient);

        PatientEntity newPatient2 = new PatientEntity(
                "뉴페이스1", "97",
                "M", "2011-03-13", "010-0002-0101", hospital
        );

        patientRepository.save(newPatient2);

        PatientEntity newPatient3 = new PatientEntity(
                "헝길동", "998877",
                "F", "2011-06-03", "010-0442-0101", hospital
        );

        patientRepository.save(newPatient3);

        PatientEntity newPatient4 = new PatientEntity(
                "뉴GFG", "998877",
                "F", "2011-03-03", "010-1232-0101", hospital
        );

        patientRepository.save(newPatient4);

    }

    @Test
    @DisplayName("환자 수정")
    public void update() {
        PatientEntity patient2 = Objects.requireNonNull(patientRepository.findPatientByPatientNm("환자2").orElse(null));
        patient2.setPatientNm("수정");
        if(em.contains(patient2)){
            em.merge(patient2);
        }
        em.flush();
        em.clear();

        PatientEntity find = Objects.requireNonNull(patientRepository.findPatientByPatientNm("수정").orElse(null));
        assertThat(find).isNotNull();
        assertThat(find.getBirth()).isEqualTo("19901215");
    }

    @Test
    @DisplayName("환자 삭제")
    public void delete() {
        PatientEntity patient2 = Objects.requireNonNull(patientRepository.findPatientByPatientNm("환자2").orElse(null));
        patientRepository.delete(patient2);
    }
}