package com.hd.patient.api.search.controller;

import com.hd.patient.api.patient.model.PatientVo;
import com.hd.patient.api.search.model.Paging;
import com.hd.patient.api.search.service.SearchService;
import com.hd.patient.common.DefaultResponse;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/1.0/search")
@Slf4j
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/{type}")
    @ApiOperation(value = "환자 정보 검색 API", notes = "키워드가 없다면 전체검색, 키워드가 있다면 해당값으로 검색")
    public ResponseEntity<Object> patientList(@PathVariable String type,
                                              @RequestParam(required = false) String keyword
                                              , Paging paging) {

        Page<PatientVo> patientList = (keyword == null)
                ? searchService.searchAll(paging) // 전체 검색
                : searchService.searchAllByType(keyword, type, paging); // 키워드로 검색
        if (patientList.isEmpty()) {
            log.info("검색 결과가 없음");
            return DefaultResponse.from(BAD_REQUEST.value(), "검색결과가 없습니다.").build();
        }
        log.info("검색 성공");
        return DefaultResponse.from(OK.value(), "검색 완료", patientList).build();
    }
}
