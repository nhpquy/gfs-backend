package com.gfs.domain.document;

import com.gfs.domain.constant.CollectionName;
import com.gfs.domain.enums.ArticleStatus;
import com.gfs.domain.enums.ArticleType;
import com.gfs.domain.enums.BlockchainType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = CollectionName.TUTOR_ARTICLES)
public class TutorArticle extends ObjectIdDocument {
    /**
     * Authorized Info
     */
    @Indexed(unique = true)
    private String article_id;
    @Indexed
    private String tutor_id;

    /**
     * General Info
     */
    private String title;
    private String cover_image;
    private ArticleType type;
    private String encrypted_hash_code;
    private String description;

    /**
     * Blockchain verify Info
     */
    private ArticleStatus status;
    private String transaction_id;
    private BlockchainType blockchain_type;
    private long issued_on;
}
