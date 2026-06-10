package com.test.courseservice.controller;

import com.test.courseservice.dto.request.CourseRequest;
import com.test.courseservice.dto.response.ApiResponse;
import com.test.courseservice.entity.Course;
import com.test.courseservice.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService service;

    @PostMapping
    public ResponseEntity<ApiResponse<Course>> createNewCourse(@RequestBody CourseRequest request)
    {
        Course newCourse = service.createNewCourse(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponse.<Course>builder()
                                .message("Created new Course")
                                .status(HttpStatus.CREATED)
                                .data(newCourse)
                                .build()
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> findCourseById(@PathVariable("id") Long request)
    {
        Course newCourse = service.findCourseById(request);

        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        ApiResponse.<Course>builder()
                                .message("Found Course")
                                .status(HttpStatus.OK)
                                .data(newCourse)
                                .build()
                );
    }
}
