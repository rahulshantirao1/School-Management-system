package com.Student.service;

import com.Student.repository.MarkSheetRepository;
import org.springframework.stereotype.Service;

@Service
public class MarkSheetService {

    private MarkSheetRepository markSheetRepository;

    public MarkSheetService(MarkSheetRepository markSheetRepository) {
        this.markSheetRepository = markSheetRepository;
    }
}
