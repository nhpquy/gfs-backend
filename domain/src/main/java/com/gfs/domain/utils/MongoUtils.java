package com.gfs.domain.utils;

import com.gfs.domain.request.IndexPagingRequest;
import com.gfs.domain.request.PagingRequest;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.Map;

public class MongoUtils {
    public static Criteria createPagingCriteria(PagingRequest request) {
        Criteria criteria = new Criteria();
        if (StringUtils.hasText(request.getFrom_hex_id())) {
            if ("previous".equals(request.getAction()))
                criteria = criteria.and("_id").gt(new ObjectId(request.getFrom_hex_id()));
            else criteria = criteria.and("_id").lt(new ObjectId(request.getFrom_hex_id()));
        }

        if (request.getFrom() > 0 && request.getTo() > 0)
            criteria = criteria.and("created_at").gte(request.getFrom()).lte(request.getTo());
        else if (request.getFrom() > 0)
            criteria = criteria.and("created_at").gte(request.getFrom());
        else if (request.getTo() > 0)
            criteria = criteria.and("created_at").lte(request.getTo());

        return criteria;
    }

    public static Criteria createPagingCriteria(IndexPagingRequest request) {
        Criteria criteria = new Criteria();
        if (request.getFrom() > 0 && request.getTo() > 0)
            criteria = criteria.and("created_at").gte(request.getFrom()).lte(request.getTo());
        else if (request.getFrom() > 0)
            criteria = criteria.and("created_at").gte(request.getFrom());
        else if (request.getTo() > 0)
            criteria = criteria.and("created_at").lte(request.getTo());

        return criteria;
    }

    public static Criteria addFilterCriteria(Map<String, Object> filter) {
        Criteria criteria = new Criteria();
        for (String key : filter.keySet()) {
            if (filter.get(key) != null)
                criteria = criteria.and(key).is(filter.get(key));
        }
        return criteria;
    }

    public static Sort getSortDirection(PagingRequest request) {
        if ("previous".equals(request.getAction()))
            return Sort.by(Sort.Direction.ASC, "_id");
        else return Sort.by(Sort.Direction.DESC, "_id");
    }
}
