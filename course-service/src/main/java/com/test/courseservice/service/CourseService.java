package com.test.courseservice.service;

import com.test.courseservice.dto.request.CourseRequest;
import com.test.courseservice.dto.response.ApiResponse;
import com.test.courseservice.entity.Course;
import com.test.courseservice.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository repository;

    public Course createNewCourse(CourseRequest request)
    {
        Course newCourse = new Course();
        newCourse.setCourseName(request.getCourseName());
        newCourse.setCapacity(request.getCapacity());
        newCourse.setCredits(request.getCredits());

        return repository.save(newCourse);
    }

    public Course findCourseById(Long courseId)
    {
        return repository.findById(courseId).orElseThrow(() -> new NoSuchElementException("Not found this course"));
    }
}
