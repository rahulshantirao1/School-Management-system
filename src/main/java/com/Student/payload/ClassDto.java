package com.Student.payload;

import lombok.Data;

import java.util.Set;

@Data
public class ClassDto {

    private String className;
    private String stream;
    private String section;
    private Set<String> subjects;
}
