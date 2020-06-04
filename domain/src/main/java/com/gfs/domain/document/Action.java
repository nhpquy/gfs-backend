package com.gfs.domain.document;

import com.gfs.domain.constant.CollectionName;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = CollectionName.ACTIONS)
@CompoundIndex(def = "{\"name\": 1, \"action\": 1}", unique = true)
public class Action extends ObjectIdDocument {

    private static final int EXPIRE_SECOND = 86400;

    private String name;
    private String action;

    @Indexed(expireAfterSeconds = EXPIRE_SECOND)
    private Date createTime;

    public Action() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Action(String name, String action) {
        this.name = name;
        this.action = action;
        createTime = new Date();
    }

    public Action(String name, String action, long ttl) {
        this.name = name;
        this.action = action;
        this.createTime = new Date(new Date().getTime() - EXPIRE_SECOND * 1000 + ttl * 1000);
    }

    public static Action createAction(String name, String action, long ttl) {
        return new Action(name, action, ttl);
    }
}