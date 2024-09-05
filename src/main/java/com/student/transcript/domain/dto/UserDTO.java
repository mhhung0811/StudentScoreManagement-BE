package com.student.transcript.domain.dto;

import com.student.transcript.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserDTO {
    @UuidGenerator
    private String id;
    private String name;
}
