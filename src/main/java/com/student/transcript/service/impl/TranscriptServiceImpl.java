package com.student.transcript.service.impl;

import com.student.transcript.domain.dto.TranscriptDTO;
import com.student.transcript.domain.entity.Subject;
import com.student.transcript.domain.entity.Transcript;
import com.student.transcript.domain.mapper.TranscriptMapper;
import com.student.transcript.repository.SubjectRepository;
import com.student.transcript.repository.TranscriptRespository;
import com.student.transcript.service.TranscriptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TranscriptServiceImpl implements TranscriptService {
    private final TranscriptRespository transcriptRespository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public TranscriptServiceImpl(TranscriptRespository transcriptRespository, SubjectRepository subjectRepository) {
        this.transcriptRespository = transcriptRespository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public Optional<TranscriptDTO> createTranscript(TranscriptDTO transcriptDTO) {
        if (transcriptRespository.findTranscriptByUserIdAndYearAndSemester(
                transcriptDTO.getUserId(),
                transcriptDTO.getYear(),
                transcriptDTO.getSemester()).isPresent()) {
            return Optional.empty();
        }

        return Optional.of(
                TranscriptMapper.toTranscriptDTO(
                        transcriptRespository.save(
                                TranscriptMapper.toTranscript(
                                        transcriptDTO))));
    }

    @Override
    public List<TranscriptDTO> findTranscriptByUserId(String user_id) {
        List<Transcript> res = transcriptRespository.findTranscriptByUserId(user_id);
        return TranscriptMapper.toTranscriptDTO(res);
    }

    @Override
    public Optional<TranscriptDTO> findTranscriptByUserIdAndYearAndSemester(String userId, String year, int semester) {
        Optional<Transcript> res = transcriptRespository.findTranscriptByUserIdAndYearAndSemester(userId, year, semester);
        return res.map(TranscriptMapper::toTranscriptDTO);
    }

    @Override
    public void deleteTranscript(String id) {
        try {
            transcriptRespository.deleteById(id);
            List<Subject> subjects = subjectRepository.findSubjectByTranscriptId(id);
            subjectRepository.deleteAll(subjects);
        } catch (final EmptyResultDataAccessException e) {
            log.debug("Attempted to delete non-existing transcript", e);
        }
    }
}