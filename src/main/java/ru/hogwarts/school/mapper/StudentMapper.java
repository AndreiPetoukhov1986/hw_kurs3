package ru.hogwarts.school.mapper;

import org.springframework.stereotype.Component;
import ru.hogwarts.school.dto.StudentDtoIn;
import ru.hogwarts.school.dto.StudentDtoOut;
import ru.hogwarts.school.entity.Student;

@Component
public class StudentMapper {
    public StudentDtoOut toDto(Student student) {
        StudentDtoOut studentDtoOut = new StudentDtoOut();
        studentDtoOut.setId(student.getId());
        studentDtoOut.setName(student.getName());
        studentDtoOut.setAge(student.getAge());
        return studentDtoOut;
    }

    public Student toEntity(StudentDtoIn studentDtoIn){
        Student student = new Student();
        student.setAge(studentDtoIn.getAge());
        student.setName(studentDtoIn.getName());
        return student;
    }
}
