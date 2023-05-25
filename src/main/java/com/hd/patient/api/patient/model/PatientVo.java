package com.hd.patient.api.patient.model;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Objects;

@Data
@NoArgsConstructor
public class PatientVo {
    @ApiModelProperty(value = "환자 ID", example = "1")
    private Long patientId;

    @ApiModelProperty(value = "병원 ID", example = "1")
    private Long hospitalId;

    @ApiModelProperty(value = "환자 이름",example = "홍길동")
    private String patientNm;

    @ApiModelProperty(value = "환자 등록 번호",example = "13ds293r2f")
    private String patientRegNum;

    @ApiModelProperty(value = "환자 성별",example = "M")
    private String gender;

    @ApiModelProperty(value = "환자 생일",example = "19901120")
    private String birth;

    @ApiModelProperty(value = "환자 휴대폰 번호",example = "01099994444")
    private String phoneNum;

    @ApiModelProperty(value = "방문 날짜", example = "20230511")
    private String visitDate;

    @ApiModelProperty(value = "환자방문 ID", example = "1")
    private Long visitId;

    @ApiModelProperty(value = "방문상태코드", example = "1")
    private String visitState;

    @QueryProjection
    public PatientVo(Long patientId, String patientNm, Long hospitalId, String gender, String patientRegNum
            ,String birth, String phoneNum, String visitDate, String visitState, Long visitId){
        this.patientId = patientId;
        this.patientNm = patientNm;
        this.patientRegNum =  patientRegNum;
        this.gender = gender;
        this.birth = birth;
        this.phoneNum = phoneNum;
        this.hospitalId = hospitalId;
        if (Objects.nonNull(visitId)) {
            this.visitId = visitId;
            this.visitDate = visitDate;
            this.visitState = visitState;
        }
    }
}
