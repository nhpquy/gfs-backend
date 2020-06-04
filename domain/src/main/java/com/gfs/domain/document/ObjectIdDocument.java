package com.gfs.domain.document;

import com.gfs.domain.utils.GsonSingleton;
import com.gfs.domain.utils.MD5;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
public class ObjectIdDocument {
    private ObjectId id;
    @Indexed
    private long created_at = System.currentTimeMillis();
    private long updated_at = System.currentTimeMillis();

    public String hash() {
        return MD5.hash(GsonSingleton.getInstance().toJson(this));
    }
}
