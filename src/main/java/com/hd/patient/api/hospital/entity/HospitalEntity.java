package com.hd.patient.api.hospital.entity;

import com.hd.patient.api.hospital.model.HospitalVo;
import lombok.*;

import javax.persistence.*;

@Entity(name = "hospitalEntity")
@Table(name = "hospitals")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class HospitalEntity {
    @Id
    @GeneratedValue
    @Column(name = "hospital_id")
    private Long hospitalId;

    @Column(name = "hospital_name")
    private String hospitalNm;

    @Column(name = "nursing_home_number")
    private String nursingHomeNum;

    @Column(name = "hospital_director_name")
    private String hospitalDirectorNm;

    public HospitalEntity(HospitalVo vo){
        this.hospitalNm = vo.getHospitalNm();
        this.nursingHomeNum = vo.getNursingHomeNum();
        this.hospitalDirectorNm = vo.getHospitalDirectorNm();
    }

    public HospitalEntity(String hospitalNm, String nursingHomeNum, String hospitalDirectorNm){
        this.hospitalNm = hospitalNm;
        this.nursingHomeNum = nursingHomeNum;
        this.hospitalDirectorNm = hospitalDirectorNm;
    }

    public void updateHospital(HospitalVo vo) {
        this.hospitalNm = vo.getHospitalNm();
        this.nursingHomeNum = vo.getNursingHomeNum();
        this.hospitalDirectorNm = vo.getHospitalDirectorNm();
    }
}
