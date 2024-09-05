package com.student.transcript.service;

import com.student.transcript.domain.dto.PageRequestDTO;
import com.student.transcript.domain.dto.SubjectTypeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface SubjectTypeService {
    List<SubjectTypeDTO> findAll();
    Optional<SubjectTypeDTO> createSubjectType(SubjectTypeDTO subjectTypeDTO);
    Optional<SubjectTypeDTO> findSubjectTypeById(String id);
    Optional<SubjectTypeDTO> findSubjectTypeByCode(String code);
    List<SubjectTypeDTO> findSubjectTypeCodeContainingOrNameContaining(String code, String name);
    void deleteSubjectType(String id);
}
