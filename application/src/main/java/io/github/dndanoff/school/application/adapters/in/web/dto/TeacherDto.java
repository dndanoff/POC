package io.github.dndanoff.school.application.adapters.in.web.dto;

import io.github.dndanoff.school.application.adapters.in.web.dto.common.Dto;
import lombok.Data;

@Data
public class TeacherDto implements Dto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
