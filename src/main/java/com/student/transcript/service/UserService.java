package com.student.transcript.service;

import com.student.transcript.domain.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> findAll();
    Optional<UserDTO> createUser(UserDTO user);
    Optional<UserDTO> findUserByUsername(String username);
    List<String> findAllYearByUserId(String userId);
    List<Integer> findAllSemesterByUserIdAndYear(String userId, String year);
    Optional<UserDTO> updateUser(String id, String name);
    void deleteUser(String id);
}
