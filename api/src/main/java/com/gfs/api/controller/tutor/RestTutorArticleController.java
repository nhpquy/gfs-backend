package com.gfs.api.controller.tutor;

import com.gfs.api.annotation.ApiRestController;
import com.gfs.domain.constant.SwaggerTag;
import com.gfs.domain.enums.AccountProfile;
import com.gfs.domain.enums.ArticleStatus;
import com.gfs.domain.enums.ArticleType;
import com.gfs.domain.model.CurrentTutorLogin;
import com.gfs.domain.request.CreateArticleRequest;
import com.gfs.domain.request.GetTutorArticlePagingRequest;
import com.gfs.domain.request.UpdateArticleRequest;
import com.gfs.domain.response.GetTutorArticleResponse;
import com.gfs.services.annotation.AccountAuthorized;
import com.gfs.services.inf.TutorArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Api(tags = SwaggerTag.article_management)
@ApiRestController(value = "/tutor/article")
public class RestTutorArticleController {

    @Autowired
    TutorArticleService tutorArticleService;

    /**
     * APIs for Articles zone
     */
    @ApiOperation(value = "Create new Articles")
    @PostMapping(value = "create")
    public GetTutorArticleResponse createArticle(@AccountAuthorized(profiles = AccountProfile.tutor)
                                                 @RequestHeader(name = "Authorization") CurrentTutorLogin currentAccountLogin,
                                                 @RequestParam(name = "title", required = false) String title,
                                                 @RequestParam(name = "cover_image", required = false) String cover_image,
                                                 @RequestParam(name = "type", required = false) ArticleType type,
                                                 @RequestParam(name = "description", required = false) String description,
                                                 @RequestParam(name = "file") MultipartFile file) {
        CreateArticleRequest request = new CreateArticleRequest(title, cover_image, type, description);
        return tutorArticleService.createArticle(request, file, currentAccountLogin);
    }

    @ApiOperation(value = "Update Articles")
    @PostMapping(value = "update")
    public GetTutorArticleResponse updateArticle(@AccountAuthorized(profiles = AccountProfile.tutor)
                                                 @RequestHeader(name = "Authorization") CurrentTutorLogin currentAccountLogin,
                                                 @RequestParam(name = "article_id") String article_id,
                                                 @RequestParam(name = "title", required = false) String title,
                                                 @RequestParam(name = "cover_image", required = false) String cover_image,
                                                 @RequestParam(name = "type", required = false) ArticleType type,
                                                 @RequestParam(name = "description", required = false) String description,
                                                 @RequestParam(name = "file", required = false) MultipartFile file) {
        UpdateArticleRequest request = new UpdateArticleRequest(article_id, title, cover_image, type, description);
        return tutorArticleService.updateArticle(request, file, currentAccountLogin);
    }

    @ApiOperation(value = "Submit Articles")
    @PostMapping(value = "submit")
    public GetTutorArticleResponse submitArticle(@AccountAuthorized(profiles = AccountProfile.tutor)
                                                 @RequestHeader(name = "Authorization") CurrentTutorLogin currentAccountLogin,
                                                 @RequestParam("articleId") String articleId) {
        return tutorArticleService.submitArticle(articleId, currentAccountLogin);
    }

    @ApiOperation(value = "Get Articles Detail")
    @PostMapping(value = "get")
    public GetTutorArticleResponse getArticleDetail(@AccountAuthorized(profiles = AccountProfile.tutor)
                                                    @RequestHeader(name = "Authorization") CurrentTutorLogin currentAccountLogin,
                                                    @RequestParam("articleId") String articleId) {
        return tutorArticleService.getArticleDetail(articleId, currentAccountLogin);
    }

    @ApiOperation(value = "List Articles in pagination format")
    @PostMapping(value = "list")
    public List<GetTutorArticleResponse> listArticlePaging(@AccountAuthorized(profiles = AccountProfile.tutor)
                                                           @RequestHeader(name = "Authorization") CurrentTutorLogin currentAccountLogin,
                                                           @RequestBody GetTutorArticlePagingRequest request) {
        if (request.getStatuses() == null)
            request.setStatuses(Arrays.asList(ArticleStatus.values()));
        return tutorArticleService.listArticlePaging(request, currentAccountLogin);
    }
    /* End Cert Template zone*/
}
