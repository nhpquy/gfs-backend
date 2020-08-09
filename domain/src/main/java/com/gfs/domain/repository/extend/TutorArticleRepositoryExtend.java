package com.gfs.domain.repository.extend;

import com.gfs.domain.document.TutorArticle;
import com.gfs.domain.request.GetTutorArticlePagingRequest;

import java.util.List;
import java.util.Map;

public interface TutorArticleRepositoryExtend {
    public TutorArticle updateDetail(String articleId, Map<String, Object> updateValues);

    public List<TutorArticle> findArticlesByTutorIdPaging(String tutorId, GetTutorArticlePagingRequest request);
}
