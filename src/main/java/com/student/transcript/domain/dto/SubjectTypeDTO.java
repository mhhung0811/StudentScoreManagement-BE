package com.student.transcript.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubjectTypeDTO {
    @UuidGenerator
    private String id;
    private String code;
    private String name;
}