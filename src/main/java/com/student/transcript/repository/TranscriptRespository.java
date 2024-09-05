package com.student.transcript.repository;

import com.student.transcript.domain.entity.Transcript;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TranscriptRespository extends JpaRepository<Transcript, String> {
    List<Transcript> findTranscriptByUserId(String id);
    List<Transcript> findTranscriptByUserIdAndYear(String id, String year);
    Optional<Transcript> findTranscriptByUserIdAndYearAndSemester(String userId, String year, int semester);
}
