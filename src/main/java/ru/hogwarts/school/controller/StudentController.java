package ru.hogwarts.school.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.dto.FacultyDtoOut;
import ru.hogwarts.school.dto.StudentDtoIn;
import ru.hogwarts.school.dto.StudentDtoOut;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public StudentDtoOut addStudent(@RequestBody StudentDtoIn studentDtoIn) {
        return studentService.addStudent(studentDtoIn);
    }

    @GetMapping("{id}")
    public StudentDtoOut findStudent(@PathVariable("id") long id) {
        return studentService.findStudent(id);
    }

    @PutMapping
    public StudentDtoOut editStudent(@PathVariable("id") long id, @RequestBody StudentDtoIn studentDtoIn) {
        return studentService.editStudent(id, studentDtoIn);
    }

    @DeleteMapping("{id}")
    public StudentDtoOut deleteStudent(@PathVariable("id") long id) {
        return studentService.deleteStudent(id);
    }

    @GetMapping
    public List<StudentDtoOut> findByAge(@RequestParam(required = false) Integer age) {
        return studentService.findByAge(age);
    }

    @GetMapping("/filter")
    public List<StudentDtoOut> findByAgeBetween(@RequestParam int ageMin, @RequestParam int ageMax) {
        return studentService.findByAgeBetween(ageMin, ageMax);
    }

    @GetMapping("/{id}/faculty")
    public FacultyDtoOut findFaculty(@PathVariable("id") long id) {
        return studentService.findFaculty(id);
    }

    @PatchMapping(value = "/{id}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public StudentDtoOut uploadAvatar(@PathVariable long id,
                                      @RequestPart("avatar") MultipartFile multipartFile) {
        return studentService.uploadAvatar(id, multipartFile);
    }
}
