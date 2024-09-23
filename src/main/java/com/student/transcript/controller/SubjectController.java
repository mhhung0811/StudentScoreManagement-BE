package com.student.transcript.controller;

import com.student.transcript.domain.dto.PageRequestDTO;
import com.student.transcript.domain.dto.SubjectDTO;
import com.student.transcript.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {
    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @CrossOrigin
    @PostMapping("/create")
    public ResponseEntity<SubjectDTO> createSubject(@RequestBody SubjectDTO subjectDTO) {
        Optional<SubjectDTO> res = subjectService.createSubject(subjectDTO);
        return res.map(subject -> new ResponseEntity<>(subject, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @CrossOrigin
    @GetMapping("/{transcriptId}")
    public ResponseEntity<List<SubjectDTO>> findSubjectByTranscriptId(@PathVariable String transcriptId) {
        List<SubjectDTO> res = subjectService.findSubjectByTranscriptId(transcriptId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/find")
    public ResponseEntity<SubjectDTO> findSubjectByTranscriptIdAndSubjectTypeId(
            @RequestParam String transcriptId,
            @RequestParam String subjectTypeId
    ) {
        Optional<SubjectDTO> res = subjectService.findSubjectByTranscriptIdAndSubjectTypeId(transcriptId, subjectTypeId);
        return res.map(subject -> new ResponseEntity<>(subject, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @CrossOrigin
    @PostMapping ("/search")
    public ResponseEntity<Page<SubjectDTO>> searchSubject(
            @RequestParam String transcriptId,
            @RequestParam String key,
            @RequestBody PageRequestDTO dto
        ) {
        Page<SubjectDTO> res = subjectService.searchSubject(transcriptId, key, dto);
        if (res.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/search/v2")
    public ResponseEntity<Page<SubjectDTO>> searchSubject(
            @RequestParam String key,
            @RequestParam Boolean name,
            @RequestParam Boolean year,
            @RequestParam Boolean semester,
            @RequestParam Boolean subjectcode,
            @RequestParam Boolean subjectname,
            @RequestBody PageRequestDTO page
    ) {
        Page<SubjectDTO> res = subjectService.findSubjectBySearch(key, name, year, semester, subjectcode, subjectname, page);
        if (res.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping("/update")
    public ResponseEntity<SubjectDTO> updateSubject(@RequestBody SubjectDTO subjectDTO) {
        Optional<SubjectDTO> res = subjectService.updateSubject(subjectDTO);
        return res.map(subject -> new ResponseEntity<>(subject, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @CrossOrigin
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteSubject(@PathVariable String id) {
        subjectService.deleteSubject(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
