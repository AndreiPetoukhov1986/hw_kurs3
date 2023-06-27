package ru.hogwarts.school.dto;

public class StudentDtoOut {
    private Long id;
    private String name;
    private int age;

    public StudentDtoOut(Long id, String name, int age) {
        this.id=id;
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StudentDtoOut() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
