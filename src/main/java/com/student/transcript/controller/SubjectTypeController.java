package com.student.transcript.controller;

import com.student.transcript.domain.dto.PageRequestDTO;
import com.student.transcript.domain.dto.SubjectDTO;
import com.student.transcript.domain.dto.SubjectTypeDTO;
import com.student.transcript.service.SubjectTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/subjecttype")
public class SubjectTypeController {
    private final SubjectTypeService subjectTypeService;

    @Autowired
    public SubjectTypeController(SubjectTypeService subjectTypeService) {
        this.subjectTypeService = subjectTypeService;
    }

    @CrossOrigin
    @PostMapping("/create")
    public ResponseEntity<SubjectTypeDTO> createSubjectType(
            @RequestBody SubjectTypeDTO subjectTypeDTO
    ) {
        Optional<SubjectTypeDTO> res = subjectTypeService.createSubjectType(subjectTypeDTO);
        return res.map(subjectType -> new ResponseEntity<>(subjectType, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<SubjectTypeDTO>> getAllSubjectType() {
        List<SubjectTypeDTO> res = subjectTypeService.findAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/id/{id}")
    public ResponseEntity<SubjectTypeDTO> getSubjectTypeById(
            @PathVariable String id
    ) {
        Optional<SubjectTypeDTO> res = subjectTypeService.findSubjectTypeById(id);
        return res.map(subjectType -> new ResponseEntity<>(subjectType, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @CrossOrigin
    @GetMapping("/{code}")
    public ResponseEntity<SubjectTypeDTO> findSubjectTypeByCode(@PathVariable String code) {
        Optional<SubjectTypeDTO> res = subjectTypeService.findSubjectTypeByCode(code);
        return res.map(subjectType -> new ResponseEntity<>(subjectType, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @CrossOrigin
    @GetMapping("/search/{key}")
    public ResponseEntity<List<SubjectTypeDTO>> searchSubjectType(
            @PathVariable String key
    ) {
        List<SubjectTypeDTO> res = subjectTypeService.findSubjectTypeCodeContainingOrNameContaining(key, key);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/search")
    public ResponseEntity<Page<SubjectTypeDTO>> searchSubjectType(
            @RequestParam String key,
            @RequestParam Boolean code,
            @RequestParam Boolean name,
            @RequestBody PageRequestDTO page
    ) {
        Page<SubjectTypeDTO> res = subjectTypeService.searchByCodeOrName(key, code, name, page);
        if (res.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteSubjectType(@PathVariable String id) {
        subjectTypeService.deleteSubjectType(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}