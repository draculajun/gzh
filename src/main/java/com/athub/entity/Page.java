package com.athub.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.util.List;

/**
 * @Author Wang wenjun
 */
public class Page {

    //当前页
    @TableField(exist = false)
    private Integer currentPage;

    //起始查询
    @TableField(exist = false)
    private Integer offset;

    //一页多少条记录
    @TableField(exist = false)
    private Integer pageSize;

    //分页内容
    @TableField(exist = false)
    private List<?> pageData;

    //记录总数
    @TableField(exist = false)
    private Integer total;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset() {
        this.offset = (this.getCurrentPage() - 1) * this.pageSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<?> getPageData() {
        return pageData;
    }

    public void setPageData(List<?> pageData) {
        this.pageData = pageData;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

}
