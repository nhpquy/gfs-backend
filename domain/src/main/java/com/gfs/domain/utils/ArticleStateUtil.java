package com.gfs.domain.utils;

import com.gfs.domain.enums.ArticleProcess;
import com.gfs.domain.enums.ArticleStatus;

import java.util.HashMap;
import java.util.Map;

public class ArticleStateUtil {
    public static Map<String, ArticleStatus> templateStates() {
        Map<String, ArticleStatus> templateStates = new HashMap<>();

        // submit process
        templateStates.put(commitState(ArticleStatus.draft, ArticleProcess.tutor_submit), ArticleStatus.preview);
        templateStates.put(commitState(ArticleStatus.preview, ArticleProcess.tutor_submit), ArticleStatus.preview);
        templateStates.put(commitState(ArticleStatus.rejected, ArticleProcess.tutor_submit), ArticleStatus.preview);

        // edit process
        templateStates.put(commitState(ArticleStatus.draft, ArticleProcess.tutor_edit), ArticleStatus.draft);
        templateStates.put(commitState(ArticleStatus.preview, ArticleProcess.tutor_edit), ArticleStatus.preview);
        templateStates.put(commitState(ArticleStatus.rejected, ArticleProcess.tutor_edit), ArticleStatus.rejected);

        // deactivate process
        templateStates.put(commitState(ArticleStatus.preview, ArticleProcess.tutor_deactivate), ArticleStatus.deactivated);
        templateStates.put(commitState(ArticleStatus.approved, ArticleProcess.tutor_deactivate), ArticleStatus.deactivated);
        templateStates.put(commitState(ArticleStatus.rejected, ArticleProcess.tutor_deactivate), ArticleStatus.deactivated);

        // admin approve
        templateStates.put(commitState(ArticleStatus.preview, ArticleProcess.admin_approve), ArticleStatus.approved);

        // admin reject
        templateStates.put(commitState(ArticleStatus.preview, ArticleProcess.admin_reject), ArticleStatus.rejected);
        templateStates.put(commitState(ArticleStatus.approved, ArticleProcess.admin_reject), ArticleStatus.rejected);

        return templateStates;
    }

    public static ArticleStatus getFinalState(ArticleStatus currentState, ArticleProcess commitProcess) {
        ArticleStatus finalState = templateStates().get(commitState(currentState, commitProcess));
        if (finalState == null) {
            throw new IllegalArgumentException(String.format("Commit process: %s - Current Article Status: %s", commitProcess, currentState));
        }
        return finalState;
    }

    public static String commitState(ArticleStatus currentStatus, ArticleProcess commitProcess) {
        return String.format("%s:%s", currentStatus, commitProcess);
    }
}

