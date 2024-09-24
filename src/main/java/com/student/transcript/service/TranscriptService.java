package com.student.transcript.service;

import com.student.transcript.domain.dto.PageRequestDTO;
import com.student.transcript.domain.dto.TranscriptDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface TranscriptService {
    Optional<TranscriptDTO> createTranscript(TranscriptDTO transcriptDTO);
    List<TranscriptDTO> findTranscriptByUserId(String user_id);
    Optional<TranscriptDTO> findTranscriptByUserIdAndYearAndSemester(String userId, String year, String semester);
    void deleteTranscript(String id);
    Page<TranscriptDTO> findTranscriptBySearch(String key, Boolean name, Boolean year, Boolean semester, PageRequestDTO page);
    Optional<TranscriptDTO> findTranscriptById(String id);
}
