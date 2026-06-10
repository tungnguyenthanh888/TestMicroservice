package com.test.student.controller;

import com.test.student.dto.request.CreateStudent;
import com.test.student.dto.request.EnrollCourseDto;
import com.test.student.dto.response.ApiResponse;
import com.test.student.dto.response.StudentResponse;
import com.test.student.entity.Student;
import com.test.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService service;

    @PostMapping
    public ResponseEntity<ApiResponse<StudentResponse>> createNewStudent(@RequestBody CreateStudent createStudent)
    {
        Student newStudent = service.createStudent(createStudent);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponse.<StudentResponse>builder()
                                .message("Created new student.")
                                .status(HttpStatus.CREATED)
                                .data(
                                        StudentResponse.builder()
                                                .id(newStudent.getId())
                                                .email(newStudent.getEmail())
                                                .name(newStudent.getName())
                                                .enrolledCourses(newStudent.getEnrolledCourses())
                                                .build()
                                )
                                .build()
                );
    }

    @PatchMapping("/enroll")
    public ResponseEntity<ApiResponse<StudentResponse>> enrollCourse(@RequestBody EnrollCourseDto request)
    {
        Student student = service.enrollCourse(request);

        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        ApiResponse.<StudentResponse>builder()
                                .message("Course is enrolled a student.")
                                .status(HttpStatus.OK)
                                .data(
                                        StudentResponse.builder()
                                                .id(student.getId())
                                                .email(student.getEmail())
                                                .name(student.getName())
                                                .enrolledCourses(student.getEnrolledCourses())
                                                .build()
                                )
                                .build()
                );
    }
}
