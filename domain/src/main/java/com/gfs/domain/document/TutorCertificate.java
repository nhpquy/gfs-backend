package com.gfs.domain.document;

import com.gfs.domain.constant.CollectionName;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Bằng cấp của giáo viên
 */
@Data
@Document(collection = CollectionName.TUTOR_CERTIFICATES)
public class TutorCertificate extends ObjectIdDocument {
    @Indexed(unique = true)
    private String certificate_id;
    @Indexed
    private String tutor_id;
    private String name;
    private String main_photo;
    private String additional_photo;
}
