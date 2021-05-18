package kg.itacademy.academy.service;

import kg.itacademy.academy.entity.Student;
import kg.itacademy.academy.model.StudentModel;

import java.util.List;

public interface StudentService {
    Student save(Student student);
    Student save(StudentModel studentModel);
    List<Student> getByName(List<Student> name);
    List<Student> getAllStudents();
    Student findById(Long id);
    Student deleteById(Long id);
    List<Student> deleteAllStudents();
}
