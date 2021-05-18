package kg.itacademy.academy.service;

import kg.itacademy.academy.entity.Course;

import java.util.List;

public interface CourseService {
    Course save(Course course);
    List<Course> getAllCourses();
    Course findById(Long id);
    Course deleteById(Long id);
    List<Course> deleteAllCourses();
}
