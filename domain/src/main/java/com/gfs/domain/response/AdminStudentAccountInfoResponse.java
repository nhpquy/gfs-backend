package com.gfs.domain.response;

import com.gfs.domain.document.StudentAccount;
import com.gfs.domain.model.aggregate.AdminStudentAggResult;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdminStudentAccountInfoResponse extends StudentAccountInfoResponse {

    private int countStudied;

    private int countStudying;

    private int countIncoming;

    private int totalCourses;

    public AdminStudentAccountInfoResponse(StudentAccount account) {
        super(account);
    }

    public AdminStudentAccountInfoResponse(AdminStudentAggResult aggResult) {
        super(aggResult.getStudent());
        this.setCountStudied(aggResult.getCountStudied());
        this.setCountStudying(aggResult.getCountStudying());
        this.setCountIncoming(aggResult.getCountIncoming());
        this.setTotalCourses(aggResult.getTotalCourses());
    }
}
