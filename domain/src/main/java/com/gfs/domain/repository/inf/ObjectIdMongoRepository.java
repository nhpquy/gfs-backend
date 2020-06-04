package com.gfs.domain.repository.inf;

import com.gfs.domain.document.ObjectIdDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ObjectIdMongoRepository<T extends ObjectIdDocument> extends MongoRepository<T, ObjectId> {
    @Query(value = "{'_id' : ?0}")
    public T findByObjectId(ObjectId id);
}
