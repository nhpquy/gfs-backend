package com.gfs.domain.document;

import com.gfs.domain.constant.CollectionName;
import com.gfs.domain.model.WorkExperience;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = CollectionName.STUDENT_ACCOUNTS)
public class StudentAccount extends Account {

    private WorkExperience current_school;
    private WorkExperience current_job;

    public StudentAccount(Account account) {
        super(account);
    }
}
