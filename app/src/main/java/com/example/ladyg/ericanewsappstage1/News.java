package com.example.ladyg.ericanewsappstage1;

import android.support.v7.widget.DialogTitle;

import java.util.List;

public class News {
    private String Title;
    private String Type;
    private String SectionId;
    private String Date;

    /**
     * Adding the constructor
     */
    public News(String title, String type, String sectionId, String date) {
        Title = title;
        Type = type;
        SectionId = sectionId;
        Date = date;
    }

    public News(double title, String type, long sectionid, String date) {

    }

    /**
     * Next add the list of  Getter; return statements
     */

    public String getTitle() {
        return Title;
    }

    public String getType() {
        return Type;
    }
    public String getSectionId() {
        return SectionId;
    }

    public String getDate() {
        return Date;
    }

    /**
     * Then adding the list of Setter
     */
    public void setSectionId(String sectionid) {
        SectionId = sectionid;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setType(String type) {
        Type = type;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getUrl() {
        return getUrl();
    }
}
