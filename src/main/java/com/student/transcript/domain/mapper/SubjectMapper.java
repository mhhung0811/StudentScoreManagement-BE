package com.student.transcript.domain.mapper;

import com.student.transcript.domain.dto.SubjectDTO;
import com.student.transcript.domain.entity.Subject;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class SubjectMapper {
    public static SubjectDTO toSubjectDTO(Subject subject) {
        return new SubjectDTO(
            subject.getId(),
            subject.getTranscriptId(),
            subject.getSubjectTypeId(),
            subject.getScoreQT(),
            subject.getScoreTH(),
            subject.getScoreGK(),
            subject.getScoreCK()
        );
    }
    public static List<SubjectDTO> toSubjectDTO(List<Subject> subjects) {
        List<SubjectDTO> res = new ArrayList<>();
        for (Subject subject : subjects) {
            res.add(toSubjectDTO(subject));
        }
        return res;
    }
    public static Subject toSubject(SubjectDTO subject) {
        return new Subject(
                subject.getId(),
                subject.getTranscriptId(),
                subject.getSubjectTypeId(),
                subject.getScoreQT(),
                subject.getScoreTH(),
                subject.getScoreGK(),
                subject.getScoreCK()
        );
    }
    public static List<Subject> toSubject(List<SubjectDTO> subjects) {
        List<Subject> res = new ArrayList<>();
        for (SubjectDTO subject : subjects) {
            res.add(toSubject(subject));
        }
        return res;
    }

    public static Page<SubjectDTO> toSubjectDTO(Page<Subject> subjects) {
        return subjects.map(SubjectMapper::toSubjectDTO);
    }
}
