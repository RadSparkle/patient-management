package com.hd.patient.api.hospital.controller;

import com.hd.patient.api.hospital.entity.HospitalEntity;
import com.hd.patient.api.hospital.model.HospitalVo;
import com.hd.patient.api.hospital.service.HospitalService;
import com.hd.patient.common.DefaultResponse;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("/api/1.0/hospital")
@Slf4j
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @PostMapping("")
    @ApiOperation(value = "병원 입력", notes = "데이터를 입력 받은후 병원을 등록하는 API")
    public ResponseEntity<Object> hospitalAdd(@RequestBody HospitalVo hospital) {
        Object result = hospitalService.addHospital(hospital);
        if (result != null){
            log.info("병원 입력 성공");
            return DefaultResponse.from(CREATED.value(), "병원 입력 성공", result).build();
        } else {
            log.error("병원 입력 실패");
            return DefaultResponse.from(BAD_REQUEST.value(), "병원 입력 실패").build();
        }
    }

    @DeleteMapping("")
    @ApiOperation(value = "병원 정보 삭제 API", notes = "입력받은 데이터로 병원 정보를 삭제하는 API")
    public ResponseEntity<Object> hospitalDelete(@RequestBody HospitalVo hospital){
        try {
            hospitalService.deleteHospital(hospital);
            log.info("병원 삭제 성공");
            return DefaultResponse.from(OK.value(), "병원 정보 삭제 완료", hospital).build();
        } catch (EmptyResultDataAccessException e) {
            log.error("병원 삭제 실패");
            return DefaultResponse.from(BAD_REQUEST.value(),"존재하지않는 병원 입니다.", hospital).build();
        }
    }


    @PatchMapping("")
    @ApiOperation(value = "병원 정보 수정 API", notes = "입력받은 데이터로 병원 정보를 수정하는 API")
    public ResponseEntity<Object> hospitalModify(@RequestBody HospitalVo hospital){
        try {
            hospitalService.modifyHospital(hospital);
            log.info("병원 수정 성공");
            return DefaultResponse.from(OK.value(), "병원 정보 수정 완료", hospital).build();
        } catch (NullPointerException e) {
            log.error("병원 수정 실패");
            return DefaultResponse.from(BAD_REQUEST.value(),"존재하지않는 병원 입니다.", hospital).build();
        }
    }

    @GetMapping("")
    @ApiOperation(value = "병원 리스트 조회 API", notes = "병원 리스트를 조회하는 API")
    public ResponseEntity<Object> hospitallist(){
        List<HospitalEntity> hospitalList = hospitalService.listHospital();
        System.out.println(hospitalList);
        if (hospitalList.isEmpty()) {
            log.error("병원 리스트 조회 실패");
            return DefaultResponse.from(BAD_REQUEST.value(), "등록된 병원이 없습니다").build();
        }
        log.info("병원 리스트 조회 성공");
        return DefaultResponse.from(OK.value(), "병원 리스트 조회 완료", hospitalList).build();
    }
}
