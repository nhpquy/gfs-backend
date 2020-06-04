package com.gfs.domain.repository.extend;

import com.gfs.domain.document.Action;

public interface ActionRepositoryExtend {

    boolean addNewAction(Action action);

    boolean removeAction(Action action);

    void updateAction(Action action);
}
