package com.hd.patient.api.patient.controller;

import com.hd.patient.api.patient.model.PatientVo;
import com.hd.patient.api.patient.service.PatientService;
import com.hd.patient.common.DefaultResponse;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;


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
            return DefaultResponse.from(CREATED.value(), "환자 입력 성공", patient).build();
        } else {
            log.error("환자 입력 실패");
            return DefaultResponse.from(BAD_REQUEST.value(), "환자 입력 실패").build();
        }
    }
}
