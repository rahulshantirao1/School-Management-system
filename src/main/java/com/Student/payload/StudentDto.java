package com.Student.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private String studentId;
    private String studentName;
    private String studentUserName;
    private String studentParent;
    private String studentEmail;
    private String studentAddress;
    private String dateOfBirth;
    private String  className;
    private String stream;
    private  String section;
}
