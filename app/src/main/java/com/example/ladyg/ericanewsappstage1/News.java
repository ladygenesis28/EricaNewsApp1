package com.example.ladyg.ericanewsappstage1;

public class News {
    private String SectionName;
    private String Type;
    private String SectionId;
    private String WebPublicationDate;
    private String WebTitle;

    /**
     * Adding the constructor
     * @param type
     * @param sectionId
     * @param sectionName
     * @param webPublicationDate
     * @param webTitle
     */
    public News( String type, String sectionId, String sectionName, String webPublicationDate, String webTitle) {
        Type = type;
        SectionId = sectionId;
        SectionName = sectionName;
        WebPublicationDate = webPublicationDate;
        WebTitle = webTitle;
    }

    /**
     * Next add the list of  Getter; return statements
     */

    public String getSectionName() {
        return SectionName;
    }

    public String getType() {
        return Type;
    }
    public String getSectionId() {
        return SectionId;
    }

    public String getWebPublicationDate() {
        return WebPublicationDate;
    }

    public String getWebTitle() {
        return WebTitle;
    }

    /**
     * Then adding the list of Setter
     */
    public void setSectionId(String sectionid) {
        SectionId = sectionid;
    }

    public void setWebTitle(String webTitle) {
        WebTitle = webTitle;
    }

    public void setType(String type) {
        Type = type;
    }

    public void setSectionName(String sectionName) {
        SectionName = sectionName;
    }

    public void setWebPublicationDate(String webPublicationDate) {
        WebPublicationDate = webPublicationDate;
    }

    public String getUrl() {
        return getUrl();
    }
}
