package com.gfs.domain.request;

import com.gfs.domain.enums.ArticleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateArticleRequest {
    @NotNull(message = "'article_id' must not be null")
    @NotEmpty(message = "'article_id' must not be empty")
    private String article_id;
    private String title;
    private String cover_image;
    private ArticleType type;
    private String description;
}
