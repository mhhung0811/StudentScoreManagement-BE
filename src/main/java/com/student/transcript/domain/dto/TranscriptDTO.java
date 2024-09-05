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
public class TranscriptDTO {
    @UuidGenerator
    private String id;
    private String userId;
    private String year;
    private int semester;
}
