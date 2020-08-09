package com.gfs.services.inf;

import com.gfs.domain.model.CurrentTutorLogin;
import com.gfs.domain.request.CreateArticleRequest;
import com.gfs.domain.request.GetTutorArticlePagingRequest;
import com.gfs.domain.request.UpdateArticleRequest;
import com.gfs.domain.response.GetTutorArticleResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
public interface TutorArticleService {
    public GetTutorArticleResponse createArticle(@Valid CreateArticleRequest request, @Valid @NotNull MultipartFile file, CurrentTutorLogin currentAccountLogin);

    public GetTutorArticleResponse updateArticle(@Valid UpdateArticleRequest request, MultipartFile file, CurrentTutorLogin currentAccountLogin);

    public GetTutorArticleResponse submitArticle(@NotNull @NotEmpty String templateId, CurrentTutorLogin currentAccountLogin);

    public List<GetTutorArticleResponse> listArticlePaging(GetTutorArticlePagingRequest request, CurrentTutorLogin currentAccountLogin);

    public GetTutorArticleResponse getArticleDetail(@NotNull @NotEmpty String templateId, CurrentTutorLogin currentAccountLogin);
}
