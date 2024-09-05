package com.student.transcript.repository;

import com.student.transcript.domain.entity.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, String> {
    List<Subject> findSubjectByTranscriptId(String id);
    List<Subject> findSubjectBySubjectTypeId(String id);
    Optional<Subject> findSubjectByTranscriptIdAndSubjectTypeId(String transcriptId, String subjectTypeId);
    Page<Subject> findSubjectByTranscriptIdAndSubjectTypeIdIn(String transcriptId, Collection<String> subjectTypeId, Pageable pageable);
}
