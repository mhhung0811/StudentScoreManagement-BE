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
public class SubjectDTO {
    @UuidGenerator
    private String id;
    private String transcriptId;
    private String subjectTypeId;
    private float scoreQT;
    private float scoreTH;
    private float scoreGK;
    private float scoreCK;
}
