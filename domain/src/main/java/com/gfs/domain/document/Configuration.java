package com.gfs.domain.document;

import com.gfs.domain.constant.CollectionName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = CollectionName.CONFIGURATIONS)
@Data
@EqualsAndHashCode(callSuper = false)
public class Configuration extends ObjectIdDocument {
    @Indexed(unique = true)
    private String key;
}
