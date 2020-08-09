package com.gfs.domain.request;

import com.gfs.domain.enums.ArticleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateArticleRequest {
    private String title;
    private String cover_image;
    private ArticleType type;
    private String description;
}
