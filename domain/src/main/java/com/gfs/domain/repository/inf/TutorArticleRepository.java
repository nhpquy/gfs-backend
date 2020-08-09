package com.gfs.domain.repository.inf;

import com.gfs.domain.document.TutorArticle;
import com.gfs.domain.repository.extend.TutorArticleRepositoryExtend;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TutorArticleRepository extends ObjectIdMongoRepository<TutorArticle>,
        TutorArticleRepositoryExtend {
    @Query(value = "{'tutor_id' : ?0}")
    public List<TutorArticle> findByTutorId(String tutorId);

    @Query(value = "{'article_id' : ?0}")
    public TutorArticle findByArticle_id(String articleId);
}
