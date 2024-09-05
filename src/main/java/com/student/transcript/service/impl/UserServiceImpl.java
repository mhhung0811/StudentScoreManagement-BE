package com.student.transcript.service.impl;

import com.student.transcript.domain.dto.UserDTO;
import com.student.transcript.domain.entity.Transcript;
import com.student.transcript.domain.entity.User;
import com.student.transcript.domain.mapper.UserMapper;
import com.student.transcript.repository.TranscriptRespository;
import com.student.transcript.repository.UserRepository;
import com.student.transcript.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final TranscriptRespository transcriptRespository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, TranscriptRespository transcriptRespository) {
        this.userRepository = userRepository;
        this.transcriptRespository = transcriptRespository;
    }

    @Override
    public List<UserDTO> findAll() {
        return UserMapper.toUserDTO(userRepository.findAll());
    }

    @Override
    public Optional<UserDTO> createUser(UserDTO user) {
        if (userRepository.findByName(user.getName()).isPresent()) return Optional.empty();
        return Optional.of(UserMapper.toUserDTO(userRepository.save(UserMapper.toUser(user))));
    }

    @Override
    public Optional<UserDTO> findUserByUsername(String username) {
        Optional<User> userRes = userRepository.findByName(username);
        return userRes.map(UserMapper::toUserDTO);
    }

    @Override
    public List<String> findAllYearByUserId(String userId) {
        HashSet<String> years = new HashSet<>();
        List<Transcript> transcripts = transcriptRespository.findTranscriptByUserId(userId);
        for (Transcript transcript : transcripts) {
            years.add(transcript.getYear());
        }
        List<String> res = new ArrayList<>(years);
        return res;
    }

    @Override
    public List<Integer> findAllSemesterByUserIdAndYear(String userId, String year) {
        List<Integer> res = new ArrayList<>();
        List<Transcript> transcripts = transcriptRespository.findTranscriptByUserIdAndYear(userId, year);
        for (Transcript transcript : transcripts) {
            res.add(transcript.getSemester());
        }
        return res;
    }

    @Override
    public Optional<UserDTO> updateUser(String id, String name) {
        if (userRepository.findByName(name).isPresent()) return Optional.empty();

        User user = new User(id, name);

        return Optional.of(UserMapper.toUserDTO(userRepository.save(user)));
    }

    @Override
    public void deleteUser(String id) {
        try {
            userRepository.deleteById(id);
            List<Transcript> transcripts = transcriptRespository.findTranscriptByUserId(id);
            transcriptRespository.deleteAll(transcripts);
        } catch (final EmptyResultDataAccessException e) {
            log.debug("Attempted to delete non-existing user", e);
        }
    }
}
