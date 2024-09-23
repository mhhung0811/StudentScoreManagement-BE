package com.student.transcript.domain.mapper;

import com.student.transcript.domain.dto.TranscriptDTO;
import com.student.transcript.domain.dto.UserDTO;
import com.student.transcript.domain.entity.Transcript;
import com.student.transcript.domain.entity.User;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class TranscriptMapper {
    public static TranscriptDTO toTranscriptDTO(Transcript transcript) {
        return new TranscriptDTO(
            transcript.getId(),
            transcript.getUserId(),
            transcript.getYear(),
            transcript.getSemester()
        );
    }
    public static List<TranscriptDTO> toTranscriptDTO(List<Transcript> transcripts) {
        List<TranscriptDTO> res = new ArrayList<>();
        for (Transcript transcript : transcripts) {
            res.add(toTranscriptDTO(transcript));
        }
        return res;
    }
    public static Page<TranscriptDTO> toTranscriptDTO(Page<Transcript> transcripts) {
        return transcripts.map(TranscriptMapper::toTranscriptDTO);
    }
    public static Transcript toTranscript(TranscriptDTO transcript) {
        return new Transcript(
                transcript.getId(),
                transcript.getUserId(),
                transcript.getYear(),
                transcript.getSemester()
        );
    }
    public static List<Transcript> toTranscript(List<TranscriptDTO> transcripts) {
        List<Transcript> res = new ArrayList<>();
        for (TranscriptDTO transcript : transcripts) {
            res.add(toTranscript(transcript));
        }
        return res;
    }
}
