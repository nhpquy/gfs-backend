package com.gfs.domain.document;

import com.gfs.domain.constant.CollectionName;
import com.gfs.domain.enums.admin.AdminAction;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = CollectionName.ADMIN_ACTIVITY_LOGS)
public class AdminActivityLog extends ObjectIdDocument {
    @Indexed
    private String admin_id;
    @Indexed
    private AdminAction action;
    private String reference_id;
    private String note;
    private Object data;
    private Object before;
    private Object after;
}
