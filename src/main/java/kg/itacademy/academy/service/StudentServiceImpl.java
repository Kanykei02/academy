package kg.itacademy.academy.service;

import kg.itacademy.academy.entity.Course;
import kg.itacademy.academy.entity.Student;
import kg.itacademy.academy.entity.User;
import kg.itacademy.academy.model.StudentModel;
import kg.itacademy.academy.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student save(StudentModel studentModel) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);
        Course course = courseService.findById(studentModel.getCourseId());

        Student student = Student.builder()
                .creatorId(user)
                .registrationDate(LocalDateTime.now())
                .fee(BigDecimal.ZERO)
                .courseId(course)
                .fullName(studentModel.getFullName())
                .build();
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getByName(String name) {
        List<Student> student = getByName(name);
        if(student != null){
            studentRepository.findAllByFullName(student);
        }
        return null;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student deleteById(Long id) {
        Student student = findById(id);
        if (student != null){
            studentRepository.delete(student);
            return student;
        }
        return null;
    }

    @Override
    public List<Student> deleteAllStudents() {
        List<Student> students = getAllStudents();
        if(students != null){
            studentRepository.deleteAll(students);
        }
        return null;
    }
}
