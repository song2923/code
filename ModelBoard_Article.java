package com.example.administrator.board.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class ModelBoard_Article implements Serializable {

    String articleno;
    String userid;
    String boardcd;
    String title;
    String content;
    Integer hit;
    Date nowdate;

    public ModelBoard_Article(String articleno, String userid, String boardcd, String title, String content, Integer hit, Date nowdate) {
        this.articleno = articleno;
        this.userid = userid;
        this.boardcd = boardcd;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.nowdate = nowdate;
    }

    public ModelBoard_Article(String userid, String title) {
        this.userid = userid;
        this.title = title;
    }

    public ModelBoard_Article() {
    }

    @Override
    public String toString() {
        return "ModelBoard_Article{" +
                "articleno=" + articleno +
                ", userid='" + userid + '\'' +
                ", boardcd='" + boardcd + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", hit=" + hit +
                ", nowdate=" + nowdate +
                '}';
    }

    public String getArticleno() {
        return articleno;
    }

    public void setArticleno(String articleno) {
        this.articleno = articleno;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getBoardcd() {
        return boardcd;
    }

    public void setBoardcd(String boardcd) {
        this.boardcd = boardcd;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getHit() {
        return hit;
    }

    public void setHit(Integer hit) {
        this.hit = hit;
    }

    public Date getNowdate() {
        return nowdate;
    }

    public void setNowdate(Date nowdate) {
        this.nowdate = nowdate;
    }

    protected ModelBoard_Article(Parcel in) {
    }



}
