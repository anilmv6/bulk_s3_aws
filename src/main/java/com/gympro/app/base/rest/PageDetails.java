package com.gympro.app.base.rest;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageDetails {
    public enum SortType {
        ASC("asc"), DSC("desc"), NONE("none");

        SortType(String sortType) {
            this.sortType = sortType;
        }
        private String sortType;

        public static SortType forValue(String sortType) {
            for (SortType sort: SortType.values()) {
                if (sort.sortType.equals(sortType)) {
                    return sort;
                }
            }
            return NONE;
        }
    }
    private Long limit;
    private SortType sort;
    private String sortBy;
    private Long pageNumber;

    public Long getOffset() {
        if (pageNumber != null && limit != null && pageNumber > 0 && limit > 0) {
            return (pageNumber - 1) * limit;
        }
        return null;
    }
}
