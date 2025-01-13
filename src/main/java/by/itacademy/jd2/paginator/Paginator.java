package by.itacademy.jd2.paginator;

import lombok.Getter;

import java.util.List;

public class Paginator<T> {

    private static final Integer DEFAULT_PAGE_SIZE = 2;
    private static final Integer DEFAULT_PAGE_NUMBER = 1;

    public static Integer checkPageNumber(Integer pageNumber) {
        if (pageNumber == null || pageNumber < 1) {
            return DEFAULT_PAGE_NUMBER;
        }

        return pageNumber;
    }

    public static Integer checkPageSize(Integer pageSize) {
        if (pageSize == null || pageSize < 1) {
            return DEFAULT_PAGE_SIZE;
        }

        return pageSize;
    }

    @Getter
    private List<T> items;
    @Getter
    private Integer pageNumber;
    @Getter
    private Integer pageSize;
    private final Long totalItems;

    public Paginator(List<T> items, Integer pageNumber, Integer pageSize, Long totalItems) {
        this.items = items;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalItems = totalItems;
    }

    public Integer getTotalPages() {
        return (int) Math.ceil((double) this.totalItems / this.pageSize);
    }
}
