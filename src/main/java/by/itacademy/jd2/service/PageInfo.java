package by.itacademy.jd2.service;

import lombok.Getter;

import java.util.List;

public class PageInfo<T> {
    @Getter
    private List<T> items;
    @Getter
    private Integer pageNumber;
    @Getter
    private Integer pageSize;
    private final Long totalItems;

    public PageInfo(List<T> items, Integer pageNumber, Integer pageSize, Long totalItems) {
        this.items = items;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalItems = totalItems;
    }

    public Integer getTotalPages() {
        return (int) Math.ceil((double) this.totalItems / this.pageSize);
    }
}
