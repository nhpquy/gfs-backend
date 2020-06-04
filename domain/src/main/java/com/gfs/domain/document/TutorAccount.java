package com.gfs.domain.document;

import com.gfs.domain.constant.CollectionName;
import com.gfs.domain.model.TutorBank;
import com.gfs.domain.model.WorkExperience;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = CollectionName.TUTOR_ACCOUNTS)
public class TutorAccount extends Account {

    @Max(value = 256, message = "Short description must be 256 characters or less")
    private String short_description;
    private String certificate;
    private String experience; // 10 năm
    private List<String> specializes; // chuyên môn ["Toán","Lý","Hoá"]
    private TutorBank bank;
    private WorkExperience current_school;

    public TutorAccount(Account account) {
        super(account);
    }
}
