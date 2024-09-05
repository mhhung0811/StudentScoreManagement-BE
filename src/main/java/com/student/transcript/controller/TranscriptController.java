package com.student.transcript.controller;

import com.student.transcript.domain.dto.TranscriptDTO;
import com.student.transcript.service.TranscriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transcript")
public class TranscriptController {
    private final TranscriptService transcriptService;

    @Autowired
    public TranscriptController(TranscriptService transcriptService) {
        this.transcriptService = transcriptService;
    }

    @CrossOrigin
    @GetMapping("/{userId}")
    public ResponseEntity<List<TranscriptDTO>> getTranscriptByUserId(@PathVariable String userId) {
        List<TranscriptDTO> res = transcriptService.findTranscriptByUserId(userId);
        return ResponseEntity.ok(res);
    }

    @CrossOrigin
    @GetMapping()
    public ResponseEntity<TranscriptDTO> getTranscriptByUserIdAndYearAndSemester(
            @RequestParam String userId,
            @RequestParam String year,
            @RequestParam int semester) {
        Optional<TranscriptDTO> res = transcriptService.findTranscriptByUserIdAndYearAndSemester(userId, year, semester);
        return res.map(transcriptDTO -> new ResponseEntity<>(transcriptDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @CrossOrigin
    @PostMapping("/create")
    public ResponseEntity<TranscriptDTO> createTranscript(@RequestBody TranscriptDTO transcriptDTO) {
        Optional<TranscriptDTO> res = transcriptService.createTranscript(transcriptDTO);
        return res.map(transcript -> new ResponseEntity<>(transcript, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @CrossOrigin
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTranscript(@PathVariable String id) {
        transcriptService.deleteTranscript(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
