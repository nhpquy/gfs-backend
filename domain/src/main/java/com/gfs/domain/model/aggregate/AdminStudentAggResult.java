package com.gfs.domain.model.aggregate;

import com.gfs.domain.document.StudentAccount;
import lombok.Data;

@Data
public class AdminStudentAggResult {

    private String _id;

    private StudentAccount student;

    private int countStudied;

    private int countStudying;

    private int countIncoming;

    private int totalCourses;
}
