package kg.itacademy.academy.controller;

import kg.itacademy.academy.entity.Course;
import kg.itacademy.academy.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping
    public Course save(@RequestBody Course course){
        return courseService.save(course);
    }

    @GetMapping
    public List<Course> getAll(){
        return courseService.getAllCourses();
    }

    @GetMapping("/{userId}")
    public Course getById(@PathVariable Long userId){
        return courseService.findById(userId);
    }

    @DeleteMapping("/{userId}")
    public Course deleteById(@PathVariable Long userId){
        return courseService.deleteById(userId);
    }

    @DeleteMapping
    public List<Course> deleteAll(){
        return courseService.deleteAllCourses();
    }
}
