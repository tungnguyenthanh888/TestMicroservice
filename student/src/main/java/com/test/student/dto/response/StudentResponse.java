package com.test.student.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StudentResponse {

    private Long id;

    private String name;

    private String email;

    private List<Long> enrolledCourses;
}
