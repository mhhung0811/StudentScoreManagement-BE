package com.student.transcript.repository;

import com.student.transcript.domain.entity.Transcript;
import jakarta.persistence.Column;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TranscriptRespository extends JpaRepository<Transcript, String> {
    List<Transcript> findTranscriptByUserId(String id);
    List<Transcript> findTranscriptByUserIdAndYear(String id, String year);
    Optional<Transcript> findTranscriptByUserIdAndYearAndSemester(String userId, String year, String semester);
//    @Query(value =
//            "SELECT t " +
//            "FROM Transcript t " +
//            "JOIN User u ON t.userId = u.id " +
//            "WHERE " +
//                "u.name LIKE CONCAT('%', :key, '%') OR " +
//                "t.year LIKE CONCAT('%', :key, '%') OR " +
//                "t.semester LIKE CONCAT('%', :key, '%')"
//    )
//    Page<Transcript> findTranscriptBySearch(
//            @Param("key") String key,
//            @Param("name") String name,
//            @Param("year") String year,
//            @Param("semester") String semester,
//            Pageable pageable
//    );

    @Query(value = "SELECT t FROM Transcript t " +
            "JOIN User u ON t.userId = u.id " +
            "WHERE ( (:name = TRUE AND u.name LIKE CONCAT('%', :key, '%')) OR " +
            "(:year = TRUE AND t.year LIKE CONCAT('%', :key, '%')) OR " +
            "(:semester = TRUE AND t.semester = :key) )")
    Page<Transcript> findTranscriptBySearch(
            @Param("key") String key,
            @Param("name") Boolean name,
            @Param("year") Boolean year,
            @Param("semester") Boolean semester,
            Pageable pageable
    );
}
