package com.gfs.domain.repository.extend;

import com.gfs.domain.document.ObjectIdDocument;
import com.gfs.domain.request.PagingRequest;

import java.util.List;
import java.util.Map;

public interface ObjectIdMongoRepositoryExtend<T extends ObjectIdDocument> {
    public List<T> findByPaging(PagingRequest pagingRequest, Map<String, Object> filter, Class<T> clazz);
}
