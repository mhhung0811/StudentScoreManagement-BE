package com.student.transcript.service;

import com.student.transcript.domain.dto.PageRequestDTO;
import com.student.transcript.domain.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> findAll();
    Page<UserDTO> searchByName(String name, PageRequestDTO dto);
    Optional<UserDTO> createUser(UserDTO user);
    Optional<UserDTO> findUserByUsername(String username);
    List<String> findAllYearByUserId(String userId);
    List<String> findAllSemesterByUserIdAndYear(String userId, String year);
    Optional<UserDTO> updateUser(String id, String name);
    void deleteUser(String id);
}
