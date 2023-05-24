package com.hd.patient.api.patient.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.apache.el.util.Validation;

@Data
@NoArgsConstructor
public class PatientVo {
    @ApiModelProperty(value = "환자 ID", example = "1")
    private int patientId;

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
}
