package com.Student.payload;

import lombok.Data;

import java.util.Set;

@Data
public class ClassDto {

    private String className;

    private Set<String> subjects;
}
