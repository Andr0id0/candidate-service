package com.example.candidateservice.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public interface SortAndPaginate {

    default PageRequest getCustomPageRequest(int page, int size, String sortField, String sortType) {

        var pageSort = Sort.by(Sort.Direction.ASC, "id");
        var pageable = PageRequest.of(page, size, pageSort);

        if (sortField != null && !sortField.trim().isEmpty()) {
            var direction = "ASC".equalsIgnoreCase(sortType) ? Sort.Direction.ASC : Sort.Direction.DESC;
            var combinatedSort = Sort.by(direction, sortField);
            pageable = PageRequest.of(page, size, combinatedSort);
        }

        return pageable;
    }

}
