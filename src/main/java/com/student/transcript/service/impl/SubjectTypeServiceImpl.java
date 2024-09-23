package com.student.transcript.service.impl;

import com.student.transcript.domain.dto.PageRequestDTO;
import com.student.transcript.domain.dto.SubjectTypeDTO;
import com.student.transcript.domain.entity.Subject;
import com.student.transcript.domain.entity.SubjectType;
import com.student.transcript.domain.mapper.SubjectTypeMapper;
import com.student.transcript.repository.SubjectRepository;
import com.student.transcript.repository.SubjectTypeRepository;
import com.student.transcript.service.SubjectTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SubjectTypeServiceImpl implements SubjectTypeService {
    private final SubjectTypeRepository subjectTypeRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectTypeServiceImpl(SubjectTypeRepository subjectTypeRepository, SubjectRepository subjectRepository) {
        this.subjectTypeRepository = subjectTypeRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<SubjectTypeDTO> findAll() {
        List<SubjectTypeDTO> res = SubjectTypeMapper.toSubjectTypeDTO(subjectTypeRepository.findAll());
        return res;
    }

    @Override
    public Optional<SubjectTypeDTO> createSubjectType(SubjectTypeDTO subjectTypeDTO) {
        if (subjectTypeRepository.findSubjectTypeByCode(subjectTypeDTO.getCode()).isPresent()) {
            return Optional.empty();
        }
        return Optional.of(
                SubjectTypeMapper.toSubjectTypeDTO(
                        subjectTypeRepository.save(
                                SubjectTypeMapper.toSubjectType(
                                        subjectTypeDTO))));
    }

    @Override
    public Optional<SubjectTypeDTO> findSubjectTypeById(String id) {
        Optional<SubjectType> res = subjectTypeRepository.findById(id);
        return res.map(SubjectTypeMapper::toSubjectTypeDTO);
    }

    @Override
    public Optional<SubjectTypeDTO> findSubjectTypeByCode(String code) {
        Optional<SubjectType> res = subjectTypeRepository.findSubjectTypeByCode(code);
        return res.map(SubjectTypeMapper::toSubjectTypeDTO);
    }


    @Override
    public List<SubjectTypeDTO> findSubjectTypeCodeContainingOrNameContaining(String code, String name) {
        List<SubjectType> res = subjectTypeRepository.findSubjectTypesByCodeContainingOrNameContaining(code, name);
        return SubjectTypeMapper.toSubjectTypeDTO(res);
    }

    @Override
    public void deleteSubjectType(String id) {
        try {
            List<Subject> subjects = subjectRepository.findSubjectBySubjectTypeId(id);
            subjectRepository.deleteAll(subjects);
            subjectTypeRepository.deleteById(id);
        } catch (final EmptyResultDataAccessException e) {
            log.debug("Attempted to delete non-existing subject type", e);
        }
    }

    @Override
    public Page<SubjectTypeDTO> searchByCodeOrName(String key, Boolean code, Boolean name, PageRequestDTO page) {
        Pageable pageable = new PageRequestDTO().getPageable(page);

        Page<SubjectType> res = subjectTypeRepository.findSubjectTypeBySearch(key, code, name, pageable);
        return SubjectTypeMapper.toSubjectTypeDTO(res);
    }
}
