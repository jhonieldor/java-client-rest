package io.spring.microservices.javarestclient.model;

public class PageResource {
    private Integer page;
    private Integer pageSize;
    private Integer totalPages;
    private Integer resultSize;


    public PageResource() {
    }

    public PageResource(Integer page, Integer pageSize, Integer totalPages, Integer resultSize) {
        this.page = page;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
        this.resultSize = resultSize;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public Integer getResultSize() {
        return resultSize;
    }
}
