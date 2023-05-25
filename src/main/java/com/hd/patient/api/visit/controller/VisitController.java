package com.hd.patient.api.visit.controller;

import com.hd.patient.api.visit.model.VisitVo;
import com.hd.patient.api.visit.service.VisitService;
import com.hd.patient.common.DefaultResponse;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/1.0/visit")
@Slf4j
public class VisitController {

    @Autowired
    private VisitService visitService;

    @PostMapping("")
    @ApiOperation(value = "환자 방문 입력", notes = "데이터를 입력 받은후 환자 방문 정보를 등록하는 API")
    public ResponseEntity<Object> visitAdd (@RequestBody VisitVo visit){
        try {
            Object result = visitService.addVisit(visit);
            log.info("방문 등록 성공");
            return DefaultResponse.from(OK.value(), "환자 방문 등록 완료", result).build();
        } catch (Exception e){
            log.info("방문 등록 실패");
            return DefaultResponse.from(BAD_REQUEST.value(), "환자 방문 등록 실패", visit).build();
        }
    }
}
