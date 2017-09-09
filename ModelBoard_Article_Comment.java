package com.example.administrator.board.model;

import android.os.Parcel;

import java.io.Serializable;
import java.util.Date;


public class ModelBoard_Article_Comment implements Serializable {

    Integer commentno;
    String articleno;
    String commentmemo;
    Date nowdate;
    String userid;

    public ModelBoard_Article_Comment(Integer commentno, String articleno, String commentmemo, Date nowdate, String userid) {
        this.commentno = commentno;
        this.articleno = articleno;
        this.commentmemo = commentmemo;
        this.nowdate = nowdate;
        this.userid = userid;
    }

    public ModelBoard_Article_Comment(String commentmemo, Date nowdate, String userid) {
        this.commentmemo = commentmemo;
        this.nowdate = nowdate;
        this.userid = userid;
    }

    public ModelBoard_Article_Comment() {
    }

    public Integer getCommentno() {
        return commentno;
    }

    public void setCommentno(Integer commentno) {
        this.commentno = commentno;
    }

    public String getArticleno() {
        return articleno;
    }

    public void setArticleno(String articleno) {
        this.articleno = articleno;
    }

    public String getCommentmemo() {
        return commentmemo;
    }

    public void setCommentmemo(String commentmemo) {
        this.commentmemo = commentmemo;
    }

    public Date getNowdate() {
        return nowdate;
    }

    public void setNowdate(Date nowdate) {
        this.nowdate = nowdate;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "ModelBoard_Article_Comment{" +
                "commentno=" + commentno +
                ", articleno=" + articleno +
                ", commentmemo='" + commentmemo + '\'' +
                ", nowdate=" + nowdate +
                ", userid='" + userid + '\'' +
                '}';
    }
}
