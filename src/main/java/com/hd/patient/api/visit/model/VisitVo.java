package com.hd.patient.api.visit.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VisitVo {
    @ApiModelProperty(value = "환자방문 ID", example = "1")
    private Long visitId;

    @ApiModelProperty(value = "병원 ID", example = "1")
    private Long hospitalId;

    @ApiModelProperty(value = "환자 ID",example = "홍길동")
    private Long patientId;

    @ApiModelProperty(value = "접수 일시",example = "13ds293r2f")
    private String visitDate;

    @ApiModelProperty(value = "방문 상태 코드",example = "M")
    private String visitStatusCode;
}
