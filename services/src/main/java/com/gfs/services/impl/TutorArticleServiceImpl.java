package com.gfs.services.impl;

import com.gfs.domain.component.IPFSClient;
import com.gfs.domain.config.ApplicationProperties;
import com.gfs.domain.document.TutorArticle;
import com.gfs.domain.enums.ArticleProcess;
import com.gfs.domain.enums.ArticleStatus;
import com.gfs.domain.model.CurrentTutorLogin;
import com.gfs.domain.repository.inf.TutorArticleRepository;
import com.gfs.domain.request.CreateArticleRequest;
import com.gfs.domain.request.GetTutorArticlePagingRequest;
import com.gfs.domain.request.UpdateArticleRequest;
import com.gfs.domain.response.GetTutorArticleResponse;
import com.gfs.domain.utils.AESEncryptor;
import com.gfs.domain.utils.ArticleStateUtil;
import com.gfs.domain.utils.ServiceExceptionUtils;
import com.gfs.domain.utils.StringUtils;
import com.gfs.services.inf.TutorArticleService;
import io.ipfs.api.MerkleNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TutorArticleServiceImpl implements TutorArticleService {

    @Autowired
    TutorArticleRepository tutorArticleRepository;

    @Autowired
    IPFSClient ipfsClient;

    @Override
    public GetTutorArticleResponse createArticle(CreateArticleRequest request, MultipartFile file, CurrentTutorLogin currentAccountLogin) {
        TutorArticle article = generateArticle(request, currentAccountLogin.getTutorAccount().getAccount_id());
        article.setEncrypted_hash_code(getEncryptedHashCode(file));
        article = tutorArticleRepository.save(article);

        return new GetTutorArticleResponse(article);
    }

    @Override
    public GetTutorArticleResponse updateArticle(UpdateArticleRequest request, MultipartFile file, CurrentTutorLogin currentAccountLogin) {
        TutorArticle article = ifBeValidArticle(request.getArticle_id(), currentAccountLogin);

        Map<String, Object> updateValues = new HashMap<>();
        if (StringUtils.hasText(request.getTitle())) {
            updateValues.put("title", request.getTitle());
        }
        if (StringUtils.hasText(request.getCover_image())) {
            updateValues.put("cover_image", request.getCover_image());
        }
        if (request.getType() != null) {
            updateValues.put("type", request.getType());
        }
        if (file != null) {
            String encryptedHashCode = getEncryptedHashCode(file);
            updateValues.put("encrypted_hash_code", encryptedHashCode);
        }
        if (StringUtils.hasText(request.getDescription())) {
            updateValues.put("description", request.getDescription());
        }

        if (updateValues.size() <= 0)
            throw ServiceExceptionUtils.missingParam("Nothing to update");

        ArticleStatus finalStatus = ArticleStateUtil.getFinalState(article.getStatus(), ArticleProcess.tutor_edit);
        updateValues.put("status", finalStatus);

        article = tutorArticleRepository.updateDetail(request.getArticle_id(), updateValues);

        return new GetTutorArticleResponse(article);
    }

    @Override
    public GetTutorArticleResponse submitArticle(String templateId, CurrentTutorLogin currentAccountLogin) {
        TutorArticle article = ifBeValidArticle(templateId, currentAccountLogin);

        Map<String, Object> updateValues = new HashMap<>();
        ArticleStatus finalStatus = ArticleStateUtil.getFinalState(article.getStatus(), ArticleProcess.tutor_submit);
        updateValues.put("status", finalStatus);
        article = tutorArticleRepository.updateDetail(templateId, updateValues);

        return new GetTutorArticleResponse(article);
    }

    @Override
    public List<GetTutorArticleResponse> listArticlePaging(GetTutorArticlePagingRequest request, CurrentTutorLogin currentAccountLogin) {
        List<TutorArticle> articles = tutorArticleRepository.findArticlesByTutorIdPaging(currentAccountLogin.getTutorAccount().getAccount_id(), request);
        return articles.stream().map(GetTutorArticleResponse::new).collect(Collectors.toList());
    }

    @Override
    public GetTutorArticleResponse getArticleDetail(String articleId, CurrentTutorLogin currentAccountLogin) {
        TutorArticle article = ifBeValidArticle(articleId, currentAccountLogin);
        return new GetTutorArticleResponse(article);
    }

    private TutorArticle ifBeValidArticle(String articleId, CurrentTutorLogin currentAccountLogin) {
        TutorArticle article = tutorArticleRepository.findByArticle_id(articleId);

        if (article == null) {
            throw ServiceExceptionUtils.articleNotFound();
        }

        if (!currentAccountLogin.getAccount().getAccount_id().equals(article.getTutor_id())) {
            throw ServiceExceptionUtils.permissionDenied();
        }

        return article;
    }

    private TutorArticle generateArticle(CreateArticleRequest request, String tutorId) {
        return TutorArticle.builder()
                .article_id(UUID.randomUUID().toString())
                .tutor_id(tutorId)
                .title(request.getTitle())
                .cover_image(request.getCover_image())
                .type(request.getType())
                .description(request.getDescription())
                .status(ArticleStatus.draft)
                .build();
    }

    private String getEncryptedHashCode(MultipartFile file) {
        MerkleNode addResult;
        try {
            addResult = ipfsClient.createContent(file.getBytes());
        } catch (Exception e) {
            log.error("Exception while getting bytes from file", e);
            throw ServiceExceptionUtils.internalServerError();
        }
        String hashCode = addResult.hash.toString();
        return AESEncryptor.encrypt(
                ApplicationProperties.getCrypto_aes_secretKey(),
                ApplicationProperties.getCrypto_aes_initVector(),
                hashCode);
    }
}
