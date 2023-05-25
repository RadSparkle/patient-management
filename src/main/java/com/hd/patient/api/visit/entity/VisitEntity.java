package com.hd.patient.api.visit.entity;

import com.hd.patient.api.hospital.entity.HospitalEntity;
import com.hd.patient.api.patient.entity.PatientEntity;
import com.hd.patient.api.visit.model.VisitVo;
import lombok.*;

import javax.persistence.*;

@Entity(name = "visit")
@Table(name = "visit")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class VisitEntity {
    @Id
    @GeneratedValue
    @Column(name="patient_visit_id")
    private Long visitId;

    @ManyToOne
    @JoinColumn(name="hospital_id")
    private HospitalEntity hospitalId;

    @ManyToOne
    @JoinColumn(name="patient_id")
    private PatientEntity patientId;

    @Column(name = "registration_date")
    private String visitDate;

    @Column(name = "visit_status_code")
    private String visitStatusCode;

    public VisitEntity(VisitVo vo) {
        this.visitDate = vo.getVisitDate();
        this.visitStatusCode = vo.getVisitStatusCode();
    }
}
