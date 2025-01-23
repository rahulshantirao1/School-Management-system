package com.Student.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class SubjectDto{
    @NotEmpty(message = "Enter Subject Name")
    @NotNull(message = "Enter Subjec")
    private String subjectName;
}
