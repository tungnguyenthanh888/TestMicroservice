package com.test.student.service;

import com.test.student.client.CourseClient;
import com.test.student.dto.request.CreateStudent;
import com.test.student.dto.request.EnrollCourseDto;
import com.test.student.dto.response.ApiResponse;
import com.test.student.dto.response.CourseResponse;
import com.test.student.entity.Student;
import com.test.student.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final CourseClient courseClient;
    private final StudentRepository repository;

    public Student createStudent(CreateStudent request)
    {
        Student newStudent = new Student();
        newStudent.setEmail(request.getEmail());
        newStudent.setName(request.getName());

        return repository.save(newStudent);
    }

    @Transactional
    public Student enrollCourse(EnrollCourseDto request)
    {
        ApiResponse<CourseResponse> response = courseClient.getCourseById(request.getCourseId());

        if (response == null || response.getData() == null) {
            throw new RuntimeException("Course not found from Course-Service!");
        }

        CourseResponse course = response.getData();
        Student student = repository.findById(request.getStudentId()).orElseThrow(() -> new NoSuchElementException("Not found student"));

        if (course.getCapacity() <= 0) {
            throw new RuntimeException("Course is full");
        }

        List<Long> enrolledCourses = student.getEnrolledCourses();

        if (enrolledCourses == null) {
            enrolledCourses = new ArrayList<>();
            student.setEnrolledCourses(enrolledCourses);
        }

        enrolledCourses.add(course.getId());

        return repository.save(student);
    }
}
