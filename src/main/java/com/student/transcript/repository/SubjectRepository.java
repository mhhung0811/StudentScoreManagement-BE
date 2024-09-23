package com.student.transcript.repository;

import com.student.transcript.domain.entity.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    @Query(value = "SELECT s FROM Subject s " +
            "JOIN Transcript t ON s.transcriptId = t.id " +
            "JOIN SubjectType st ON s.subjectTypeId = st.id " +
            "JOIN User u ON  t.userId = u.id " +
            "WHERE ( (:name = TRUE AND u.name LIKE CONCAT('%', :key, '%')) OR " +
            "(:year = TRUE AND t.year LIKE CONCAT('%', :key, '%')) OR " +
            "(:semester = TRUE AND t.semester LIKE CONCAT('%', :key, '%')) OR " +
            "(:subjectcode = TRUE AND st.code LIKE CONCAT('%', :key, '%')) OR " +
            "(:subjectname = TRUE AND st.name LIKE CONCAT('%', :key, '%')))"
    )
    Page<Subject> findSubjectBySearch(
            @Param("key") String key,
            @Param("name") Boolean name,
            @Param("year") Boolean year,
            @Param("semester") Boolean semester,
            @Param("subjectcode") Boolean subjectcode,
            @Param("subjectname") Boolean subjectname,
            Pageable pageable
    );
}
