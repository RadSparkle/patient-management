package com.hd.patient.api.patient.controller;

import com.hd.patient.api.patient.model.PatientVo;
import com.hd.patient.api.patient.service.PatientService;
import com.hd.patient.common.DefaultResponse;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;


@RestController
@RequestMapping("/api/1.0/patient")
@Slf4j
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("")
    @ApiOperation(value = "환자 입력", notes = "데이터를 입력 받은후 환자를 등록하는 API")
    public ResponseEntity<Object> patientAdd(@RequestBody PatientVo patient) {
        Object result = patientService.addPatient(patient);
        if (result != null){
            log.info("환자 입력 성공");
            return DefaultResponse.from(CREATED.value(), "환자 입력 성공", result).build();
        } else {
            log.error("환자 입력 실패");
            return DefaultResponse.from(BAD_REQUEST.value(), "환자 입력 실패").build();
        }
    }

    @DeleteMapping("")
    @ApiOperation(value = "환자 정보 삭제 API", notes = "입력받은 데이터로 환자 정보를 삭제하는 API")
    public ResponseEntity<Object> patientDelete(@RequestBody PatientVo patient){
        try {
            patientService.deletePatient(patient);
            log.info("환자 삭제 성공");
            return DefaultResponse.from(OK.value(), "환자 정보 삭제 완료", patient).build();
        } catch (EmptyResultDataAccessException e) {
            log.error("환자 삭제 실패");
            return DefaultResponse.from(BAD_REQUEST.value(),"존재하지않는 환자 입니다.", patient).build();
        }
    }

    @PatchMapping("")
    @ApiOperation(value = "환자 정보 수정 API", notes = "입력받은 데이터로 환자 정보를 수정하는 API")
    public ResponseEntity<Object> patientModify(@RequestBody PatientVo patient){
        try {
            patientService.modifyPatient(patient);
            log.info("환자 수정 성공");
            return DefaultResponse.from(OK.value(), "환자 정보 수정 완료", patient).build();
        } catch (NullPointerException e) {
            log.error("환자 수정 실패");
            return DefaultResponse.from(BAD_REQUEST.value(),"존재하지않는 환자 입니다.", patient).build();
        }
    }
}
