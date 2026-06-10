package com.test.student.client;

import com.test.student.dto.response.ApiResponse;
import com.test.student.dto.response.CourseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "course-service")
public interface CourseClient {
    @GetMapping("/api/courses/{id}")
    ApiResponse<CourseResponse> getCourseById(@PathVariable("id") Long id);
}
