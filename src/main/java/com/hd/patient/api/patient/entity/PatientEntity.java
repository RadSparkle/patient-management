package com.hd.patient.api.patient.entity;

import com.hd.patient.api.hospital.entity.HospitalEntity;
import com.hd.patient.api.patient.model.PatientVo;
import com.hd.patient.api.visit.entity.VisitEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "patients")
@Table(name = "patients")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter @Setter
public class PatientEntity {
    @Id
    @GeneratedValue
    @Column(name="patient_id")
    private Long patientId;

    @ManyToOne
    @JoinColumn(name="hospital_id")
    private HospitalEntity hospitalId;

    @Column(name = "patient_name")
    private String patientNm;

    @Column(name = "patient_registration_number")
    private String patientRegNum;

    @Column(name = "gender_code")
    private String gender;

    @Column(name = "birth_date")
    private String birth;

    @Column(name = "mobile_phone")
    private String phoneNum;

    @OneToMany(mappedBy = "patientId", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<VisitEntity> visit = new ArrayList<>();

    public PatientEntity(PatientVo vo) {
        this.patientNm = vo.getPatientNm();
        this.patientRegNum = vo.getPatientRegNum();
        this.gender = vo.getGender();
        this.birth = vo.getBirth();
        this.phoneNum = vo.getPhoneNum();
    }

    public void updatePatient(PatientVo vo) {
        this.patientNm = vo.getPatientNm();
        this.gender = vo.getGender();
        this.birth = vo.getBirth();
        this.phoneNum = vo.getPhoneNum();
    }

}
