package com.gfs.domain.request;

import com.gfs.domain.enums.ArticleStatus;
import lombok.Data;

import java.util.List;

@Data
public class GetTutorArticlePagingRequest extends PagingRequest {
    private List<ArticleStatus> statuses;
}
