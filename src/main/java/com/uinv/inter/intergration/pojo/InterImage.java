package com.uinv.inter.intergration.pojo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name="interimage")
public class InterImage {
    @Id
    @Column(name = "interid")
    private String interId;
    @Column(name = "interimage")
    private String interImage;
    @Column(name = "imagecreatetime")
    private Timestamp imageCreateTime;
    @Column(name = "imageupdatetime")
    private Timestamp imageUpdateTime;

    public InterImage(){}

    public InterImage(String interId, String interImage){
        this.interId = interId;
        this.interImage = interImage;
    }

    public String getInterId() {
        return interId;
    }

    public void setInterId(String interId) {
        this.interId = interId;
    }

    public String getInterImage() {
        return interImage;
    }

    public void setInterImage(String interImage) {
        this.interImage = interImage;
    }

    public Timestamp getImageCreateTime() {
        return imageCreateTime;
    }

    public void setImageCreateTime(Timestamp imageCreateTime) {
        this.imageCreateTime = imageCreateTime;
    }

    public Timestamp getImageUpdateTime() {
        return imageUpdateTime;
    }

    public void setImageUpdateTime(Timestamp imageUpdateTime) {
        this.imageUpdateTime = imageUpdateTime;
    }
}
