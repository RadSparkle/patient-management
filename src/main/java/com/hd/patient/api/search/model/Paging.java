package com.hd.patient.api.search.model;

import lombok.Data;

@Data
public class Paging {
    private int pageNum = 1;
    private int pageSize = 10;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int rowCount) {
        this.pageSize = pageSize;
    }
    public int getOffset(){
        pageNum = Math.max(pageNum, 1);
        return (pageNum - 1) * pageSize;
    }
}
