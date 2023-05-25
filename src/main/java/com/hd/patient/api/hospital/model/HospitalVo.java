package com.hd.patient.api.hospital.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
public class HospitalVo {

    @ApiModelProperty(value = "병원 ID", example = "1")
    private Long hospitalId;

    @ApiModelProperty(value = "병원 이름", example = "홍길동병원")
    private String hospitalNm;

    @ApiModelProperty(value = "요양기관번호", example = "dfsdk23md")
    private String nursingHomeNum;

    @ApiModelProperty(value = "병원장명", example = "홍길동")
    private String hospitalDirectorNm;
}
