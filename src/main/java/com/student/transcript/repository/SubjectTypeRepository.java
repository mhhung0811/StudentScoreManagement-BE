package com.student.transcript.repository;

import com.student.transcript.domain.dto.SubjectTypeDTO;
import com.student.transcript.domain.entity.SubjectType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectTypeRepository extends JpaRepository<SubjectType, String> {
    Optional<SubjectType> findSubjectTypeByCode(String code);
    List<SubjectType> findSubjectTypesByCodeContainingOrNameContaining(String code, String name);
    @Query( value = "SELECT st FROM SubjectType st " +
            "WHERE (" +
            "(:code = TRUE AND st.code LIKE CONCAT('%', :key, '%')) OR " +
            "(:name = TRUE AND st.name LIKE CONCAT('%', :key, '%')) )"
    )
    Page<SubjectType> findSubjectTypeBySearch(
            @Param("key") String key,
            @Param("code") Boolean code,
            @Param("name") Boolean name,
            Pageable pageable
    );
}
