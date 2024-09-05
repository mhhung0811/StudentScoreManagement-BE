package com.student.transcript.service;

import com.student.transcript.domain.dto.TranscriptDTO;

import java.util.List;
import java.util.Optional;

public interface TranscriptService {
    Optional<TranscriptDTO> createTranscript(TranscriptDTO transcriptDTO);
    List<TranscriptDTO> findTranscriptByUserId(String user_id);
    Optional<TranscriptDTO> findTranscriptByUserIdAndYearAndSemester(String userId, String year, int semester);
    void deleteTranscript(String id);
}
