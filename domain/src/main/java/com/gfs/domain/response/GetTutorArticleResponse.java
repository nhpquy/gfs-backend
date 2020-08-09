package com.gfs.domain.response;

import com.gfs.domain.document.TutorArticle;
import com.gfs.domain.enums.ArticleStatus;
import com.gfs.domain.enums.ArticleType;
import com.gfs.domain.enums.BlockchainType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetTutorArticleResponse extends DocumentResponse {
    private String article_id;
    private String tutor_id;
    private String title;
    private String cover_image;
    private ArticleType type;
    private String encrypted_hash_code;
    private String description;
    private String transaction_id;
    private ArticleStatus status;
    private BlockchainType blockchain_type;
    private long issued_on;

    public GetTutorArticleResponse(TutorArticle article) {
        super(article);
        this.setTutor_id(article.getTutor_id());
        this.setArticle_id(article.getArticle_id());
        this.setTitle(article.getTitle());
        this.setCover_image(article.getCover_image());
        this.setType(article.getType());
        this.setEncrypted_hash_code(article.getEncrypted_hash_code());
        this.setDescription(article.getDescription());
        this.setTransaction_id(article.getTransaction_id());
        this.setStatus(article.getStatus());
        this.setBlockchain_type(article.getBlockchain_type());
        this.setIssued_on(article.getIssued_on());
    }
}
