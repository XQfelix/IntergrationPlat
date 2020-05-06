package com.uinv.inter.intergration.pojo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "interdoc")
public class InterDoc {

    @Id
    @Column(name = "interid")
    private String interId;
    @Column(name = "intername")
    private String interName;
    @Column(name = "intertag")
    private String interTag;
    @Column(name = "dixtag")
    private String dixTag;
    @Column(name = "interdesc")
    private String interDesc;
    @Column(name = "interimage")
    private String interImage;
    @Column(name = "intercode")
    private String interCode;
    @Column(name = "intereditor")
    private String interEditor;
    @Column(name = "intercreatetime")
    private String interCreateTime;
    @Column(name = "interupdatetime")
    private Timestamp interUpdateTime;

    public InterDoc() {
    }

    public String getDixTag() {
        return dixTag;
    }

    public void setDixTag(String dixTag) {
        this.dixTag = dixTag;
    }

    public String getInterId() {
        return interId;
    }

    public void setInterId(String interId) {
        this.interId = interId;
    }

    public String getInterName() {
        return interName;
    }

    public String getInterEditor() {
        return interEditor;
    }

    public void setInterEditor(String interEditor) {
        this.interEditor = interEditor;
    }

    public void setInterName(String interName) {
        this.interName = interName;
    }

    public String getInterTag() {
        return interTag;
    }

    public void setInterTag(String interTag) {
        this.interTag = interTag;
    }

    public String getInterDesc() {
        return interDesc;
    }

    public void setInterDesc(String interDesc) {
        this.interDesc = interDesc;
    }

    public String getInterImage() {
        return interImage;
    }

    public void setInterImage(String interImage) {
        this.interImage = interImage;
    }

    public String getInterCode() {
        return interCode;
    }

    public void setInterCode(String interCode) {
        this.interCode = interCode;
    }

    public String getInterCreateTime() {
        return interCreateTime;
    }

    public void setInterCreateTime(String interCreateTime) {
        this.interCreateTime = interCreateTime;
    }

    public Timestamp getInterUpdateTime() {
        return interUpdateTime;
    }

    public void setInterUpdateTime(Timestamp interUpdateTime) {
        this.interUpdateTime = interUpdateTime;
    }


}
