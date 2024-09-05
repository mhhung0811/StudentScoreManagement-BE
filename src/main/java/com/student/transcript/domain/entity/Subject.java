package com.student.transcript.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "subjects")

public class Subject {
    @Id
    @UuidGenerator
    private String id;
    private String transcriptId;
    private String subjectTypeId;
    private float scoreQT;
    private float scoreTH;
    private float scoreGK;
    private float scoreCK;
}
