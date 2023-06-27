package ru.hogwarts.school.dto;

public class FacultyDtoOut {
    private Long id;
    private String name;
    private String color;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FacultyDtoOut(Long id, String name, String color) {
        this.id=id;
        this.name = name;
        this.color = color;
    }

    public FacultyDtoOut() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
