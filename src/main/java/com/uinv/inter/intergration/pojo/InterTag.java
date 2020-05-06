package com.uinv.inter.intergration.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name="intertag")
public class InterTag {

    @Id
    @Column(name = "tagid")
    private String tagId;
    @Column(name = "tagname")
    private String tagName;
    @Column(name = "tagcreatetime")
    private Timestamp tagCreateTime;
    @Column(name = "tagupdatetime")
    private Timestamp tagUpdateTime;

    public InterTag(String tagId, String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
    }

    public InterTag(){}

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Timestamp getTagCreateTime() {
        return tagCreateTime;
    }

    public void setTagCreateTime(Timestamp tagCreateTime) {
        this.tagCreateTime = tagCreateTime;
    }

    public Timestamp getTagUpdateTime() {
        return tagUpdateTime;
    }

    public void setTagUpdateTime(Timestamp tagUpdateTime) {
        this.tagUpdateTime = tagUpdateTime;
    }
}
