package kg.itacademy.academy.controller;

import kg.itacademy.academy.entity.Student;
import kg.itacademy.academy.model.StudentModel;
import kg.itacademy.academy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getAll(){
        return studentService.getAllStudents();
    }

    @PostMapping
    public Student createOrUpdate(@RequestBody StudentModel studentModel) {
        return studentService.save(studentModel);
    }

    @GetMapping("/{listId}")
    public Student getById(@PathVariable Long listId){
        return studentService.findById(listId);
    }

    @GetMapping("/listName")
    public List<Student> getName(@PathVariable List<Student> listName){
        return studentService.getByName(listName);
    }

    @DeleteMapping("/{listId}")
    public Student deleteById(@PathVariable Long listId){
        return studentService.deleteById(listId);
    }

    @DeleteMapping
    public List<Student> deleteAll(){
        return studentService.deleteAllStudents();
    }
}
