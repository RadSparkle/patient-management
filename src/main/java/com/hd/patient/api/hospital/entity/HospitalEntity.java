package com.hd.patient.api.hospital.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "hospital")
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
}
