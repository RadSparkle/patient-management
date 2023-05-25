package com.hd.patient.api.search.model;

import com.hd.patient.enums.SearchEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SearchVo {
    @ApiModelProperty(value = "검색 키워드", example = "홍길동")
    private String keyword;

    @ApiModelProperty(value = "검색 조건 타입", example = "name")
    private SearchEnum type;

    public SearchVo(String keyword, SearchEnum type) {
        this.keyword = getKeyword();
        this.type = type;
    }
}
