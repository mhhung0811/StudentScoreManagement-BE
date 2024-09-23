package com.student.transcript.domain.mapper;

import com.student.transcript.domain.dto.SubjectTypeDTO;
import com.student.transcript.domain.dto.UserDTO;
import com.student.transcript.domain.entity.SubjectType;
import com.student.transcript.domain.entity.User;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class SubjectTypeMapper {
    public static SubjectTypeDTO toSubjectTypeDTO(SubjectType subjectType) {
        return new SubjectTypeDTO(
                subjectType.getId(),
                subjectType.getCode(),
                subjectType.getName()
        );
    }
    public static List<SubjectTypeDTO> toSubjectTypeDTO(List<SubjectType> subjectTypes) {
        List<SubjectTypeDTO> res = new ArrayList<>();
        for (SubjectType subjectType : subjectTypes) {
            res.add(toSubjectTypeDTO(subjectType));
        }
        return res;
    }
    public static SubjectType toSubjectType(SubjectTypeDTO subjectType) {
        return new SubjectType(
                subjectType.getId(),
                subjectType.getCode(),
                subjectType.getName()
        );
    }
    public static List<SubjectType> toSubjectType(List<SubjectTypeDTO> subjectTypes) {
        List<SubjectType> res = new ArrayList<>();
        for (SubjectTypeDTO subjectType : subjectTypes) {
            res.add(toSubjectType(subjectType));
        }
        return res;
    }

    public static Page<SubjectTypeDTO> toSubjectTypeDTO(Page<SubjectType> subjectTypes) {
        return subjectTypes.map(SubjectTypeMapper::toSubjectTypeDTO);
    }
}