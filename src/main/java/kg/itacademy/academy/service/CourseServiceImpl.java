package kg.itacademy.academy.service;

import kg.itacademy.academy.entity.Course;
import kg.itacademy.academy.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course findById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public Course deleteById(Long id) {
        Course course = findById(id);
        if (course != null){
            courseRepository.delete(course);
            return course;
        }
        return null;
    }

    @Override
    public List<Course> deleteAllCourses() {
        List<Course> courses = getAllCourses();
        if(courses != null){
            courseRepository.deleteAll(courses);
        }
        return null;
    }
}
