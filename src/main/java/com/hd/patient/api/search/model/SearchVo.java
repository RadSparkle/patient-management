package com.hd.patient.api.search.model;

import com.hd.patient.enums.SearchEnum;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SearchVo {
    private String keyword;

    private SearchEnum type;

    public SearchVo(String keyword, SearchEnum type) {
        this.keyword = getKeyword();
        this.type = type;
    }
}
