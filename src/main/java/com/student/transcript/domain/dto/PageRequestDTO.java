package com.student.transcript.domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Objects;

@Getter
@Setter
public class PageRequestDTO {
    private Integer pageNo = 0;

    private Integer pageSize = 10;

    public Pageable getPageable(PageRequestDTO dto) {
        Integer page = Objects.nonNull(dto.getPageNo()) ? dto.getPageNo() : this.pageNo;
        Integer size = Objects.nonNull(dto.getPageSize()) ? dto.getPageSize() : this.pageSize;

        PageRequest request = PageRequest.of(page, size);
        return (Pageable) request;
    }
}
