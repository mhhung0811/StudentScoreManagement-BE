package com.student.transcript.controller;

import com.student.transcript.domain.dto.UserDTO;
import com.student.transcript.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin()
    @GetMapping()
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> userDTOS = userService.findAll();
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    @CrossOrigin()
    @GetMapping(path = "/{name}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String name) {
        Optional<UserDTO> res = userService.findUserByUsername(name);
        return res.map(userDTO -> new ResponseEntity<>(userDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @CrossOrigin
    @GetMapping(path = "/year/{id}")
    public ResponseEntity<List<String>> getAllYearByUserId(
        @PathVariable String id
    ) {
        return new ResponseEntity<>(userService.findAllYearByUserId(id), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(path = "/semester")
    public ResponseEntity<List<Integer>> getAllSemesterByUserIdAndYear(
        @RequestParam String id,
        @RequestParam String year
    ) {
        return new ResponseEntity<>(userService.findAllSemesterByUserIdAndYear(id, year), HttpStatus.OK);
    }

    @CrossOrigin()
    @PostMapping(path = "/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user) {
        Optional<UserDTO> savedUser = userService.createUser(user);
        return savedUser.map(userDTO -> new ResponseEntity<>(userDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @CrossOrigin()
    @PutMapping(path = "/save")
    public ResponseEntity<UserDTO> saveUser(@RequestParam String id, @RequestParam String name) {
        Optional<UserDTO> savedUser = userService.updateUser(id, name);
        return savedUser.map(userDTO -> new ResponseEntity<>(userDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @CrossOrigin()
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}