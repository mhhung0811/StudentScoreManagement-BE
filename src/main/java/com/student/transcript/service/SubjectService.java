package com.student.transcript.service;

import com.student.transcript.domain.dto.PageRequestDTO;
import com.student.transcript.domain.dto.SubjectDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface SubjectService {
    Optional<SubjectDTO> createSubject(SubjectDTO subjectDTO);
    List<SubjectDTO> findSubjectByTranscriptId(String id);
    List<SubjectDTO> findSubjectBySubjectTypeId(String id);
    Optional<SubjectDTO> findSubjectByTranscriptIdAndSubjectTypeId(String transcriptId, String subjectTypeId);
    Page<SubjectDTO> searchSubject(String transcriptId, String key, PageRequestDTO dto);
    Optional<SubjectDTO> updateSubject(SubjectDTO subjectDTO);
    void deleteSubject(String id);
}
