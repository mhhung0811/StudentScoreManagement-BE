package com.student.transcript.service.impl;

import com.student.transcript.domain.dto.PageRequestDTO;
import com.student.transcript.domain.dto.SubjectDTO;
import com.student.transcript.domain.entity.Subject;
import com.student.transcript.domain.entity.SubjectType;
import com.student.transcript.domain.mapper.SubjectMapper;
import com.student.transcript.repository.SubjectRepository;
import com.student.transcript.repository.SubjectTypeRepository;
import com.student.transcript.repository.TranscriptRespository;
import com.student.transcript.service.SubjectService;
import com.student.transcript.utilities.Patcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    private final TranscriptRespository transcriptRespository;
    private final SubjectTypeRepository subjectTypeRepository;

    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository, TranscriptRespository transcriptRespository, SubjectTypeRepository subjectTypeRepository) {
        this.subjectRepository = subjectRepository;
        this.transcriptRespository = transcriptRespository;
        this.subjectTypeRepository = subjectTypeRepository;
    }

    @Override
    public Optional<SubjectDTO> createSubject(SubjectDTO subjectDTO) {
        if (transcriptRespository.findById(subjectDTO.getTranscriptId()).isEmpty() ||
                subjectTypeRepository.findById(subjectDTO.getSubjectTypeId()).isEmpty() ||
                subjectRepository.findSubjectByTranscriptIdAndSubjectTypeId(subjectDTO.getTranscriptId(),subjectDTO.getSubjectTypeId()).isPresent()
        )
            return Optional.empty();

        return Optional.of(
                SubjectMapper.toSubjectDTO(
                        subjectRepository.save(
                                SubjectMapper.toSubject(
                                        subjectDTO)
                        )
                )
        );
    }

    @Override
    public List<SubjectDTO> findSubjectByTranscriptId(String id) {
        List<Subject> res = subjectRepository.findSubjectByTranscriptId(id);
        return SubjectMapper.toSubjectDTO(res);
    }

    @Override
    public List<SubjectDTO> findSubjectBySubjectTypeId(String id) {
        List<Subject> res = subjectRepository.findSubjectBySubjectTypeId(id);
        return SubjectMapper.toSubjectDTO(res);
    }

    @Override
    public Optional<SubjectDTO> findSubjectByTranscriptIdAndSubjectTypeId(String transcriptId, String subjectTypeId) {
        Optional<Subject> res = subjectRepository.findSubjectByTranscriptIdAndSubjectTypeId(transcriptId, subjectTypeId);
        return res.map(SubjectMapper::toSubjectDTO);
    }

    @Override
    public Page<SubjectDTO> searchSubject(String transcriptId, String key, PageRequestDTO dto) {
        Pageable pageable = new PageRequestDTO().getPageable(dto);

        List<SubjectType> subjectTypes = subjectTypeRepository.findSubjectTypesByCodeContainingOrNameContaining(key, key);
        List<String> ids = new ArrayList<>();
        for (SubjectType subjectType : subjectTypes) {
            ids.add(subjectType.getId());
        }
        Page<Subject> res = subjectRepository.findSubjectByTranscriptIdAndSubjectTypeIdIn(transcriptId, ids, pageable);
        return SubjectMapper.toSubjectDTO(res);
    }

    @Override
    public Page<SubjectDTO> findSubjectBySearch(
            String key,
            Boolean name,
            Boolean year,
            Boolean semester,
            Boolean subjectcode,
            Boolean subjectname,
            PageRequestDTO page
    ) {
        Pageable pageable = new PageRequestDTO().getPageable(page);

        Page<Subject> res = subjectRepository.findSubjectBySearch(key, name, year, semester, subjectcode, subjectname, pageable);
        return SubjectMapper.toSubjectDTO(res);
    }


    @Override
    public Optional<SubjectDTO> updateSubject(SubjectDTO subjectDTO) {
        Optional<Subject> existSubject = subjectRepository.findById(subjectDTO.getId());
        if (existSubject.isEmpty()) return Optional.empty();

        SubjectDTO existSubjectDTO = SubjectMapper.toSubjectDTO(existSubject.get());
        try {
            Patcher.subjectDTOPatcher(existSubjectDTO, subjectDTO);
            Subject res = subjectRepository.save(SubjectMapper.toSubject(existSubjectDTO));
            return Optional.of(SubjectMapper.toSubjectDTO(res));
        } catch (Exception e) {
            log.debug(e.toString());
        }
        return Optional.empty();
    }

    @Override
    public void deleteSubject(String id) {
        try {
            subjectRepository.deleteById(id);
        } catch (final EmptyResultDataAccessException e) {
            log.debug("Attempted to delete non-existing subject", e);
        }
    }
}
