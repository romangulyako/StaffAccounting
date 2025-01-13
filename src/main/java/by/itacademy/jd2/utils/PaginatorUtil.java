package by.itacademy.jd2.utils;

public class PaginatorUtil {
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
}
