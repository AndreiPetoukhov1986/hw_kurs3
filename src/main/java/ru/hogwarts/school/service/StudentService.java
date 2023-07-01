package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.dto.FacultyDtoOut;
import ru.hogwarts.school.dto.StudentDtoIn;
import ru.hogwarts.school.dto.StudentDtoOut;
import ru.hogwarts.school.entity.Avatar;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.mapper.FacultyMapper;
import ru.hogwarts.school.mapper.StudentMapper;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;

    private final StudentMapper studentMapper;
    private final FacultyMapper facultyMapper;

    private final AvatarService avatarService;

    public StudentService(StudentRepository studentRepository,
                          FacultyRepository facultyRepository,
                          StudentMapper studentMapper,
                          FacultyMapper facultyMapper,
                          AvatarService avatarService) {
        this.studentRepository = studentRepository;
        this.facultyRepository=facultyRepository;
        this.studentMapper = studentMapper;
        this.facultyMapper=facultyMapper;
        this.avatarService=avatarService;
    }

    public StudentDtoOut addStudent(StudentDtoIn studentDtoIn) {
        return studentMapper.toDto(
                studentRepository.save(
                        studentMapper.toEntity(studentDtoIn)));
    }

    public StudentDtoOut findStudent(long id) {
        return studentRepository.findById(id)
                .map(studentMapper::toDto)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    public StudentDtoOut editStudent(long id, StudentDtoIn studentDtoIn) {
        return studentRepository.findById(id)
                .map(oldStudent -> {
                    oldStudent.setAge(studentDtoIn.getAge());
                    oldStudent.setName(studentDtoIn.getName());
                    Optional.ofNullable(studentDtoIn.getFacultyId())
                            .ifPresent(facultyId->oldStudent.setFaculty(facultyRepository.findById(facultyId)
                                    .orElseThrow(()->new FacultyNotFoundException(facultyId))));
                    return studentMapper.toDto(studentRepository.save(oldStudent));
                })
                .orElseThrow(() -> new FacultyNotFoundException(id));
    }

    public StudentDtoOut deleteStudent(long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        studentRepository.delete(student);
        return studentMapper.toDto(student);
    }

    public List<StudentDtoOut> findByAge(Integer age) {
        return Optional.ofNullable(age)
                .map(studentRepository::findAllByAge)
                .orElseGet(studentRepository::findAll).stream()
                .map(studentMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<StudentDtoOut> findByAgeBetween(int ageMin, int ageMax) {
        return studentRepository.findByAgeBetween(ageMin, ageMax).stream()
                .map(studentMapper::toDto)
                .collect(Collectors.toList());
    }

    public FacultyDtoOut findFaculty(long id) {
            return studentRepository.findById(id)
                    .map(Student::getFaculty)
                    .map(facultyMapper::toDto)
                    .orElseThrow(()->new StudentNotFoundException(id));
        }

    public StudentDtoOut uploadAvatar(long id, MultipartFile multipartFile) {
        Student student = studentRepository.findById(id)
                .orElseThrow(()->new StudentNotFoundException(id));
        Avatar avatar = avatarService.create(student, multipartFile);
        StudentDtoOut studentDtoOut = studentMapper.toDto(student);
        studentDtoOut.setAvatarUrl("/avatar/"+avatar.getId()+"/from-db");
        return studentDtoOut;

    }
}
