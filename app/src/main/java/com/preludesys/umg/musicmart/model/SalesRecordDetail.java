package com.preludesys.umg.musicmart.model;

import java.util.List;

/**
 * Created by varunsundaramoorthy on 4/24/14.
 */
public class SalesRecordDetail extends SalesRecord {
    private static final long serialVersionUID = 694379600452602646L;
    private String country;
    private String date;
    private String itunesPreviousRank;
    private String itunesRank;
    private String ld;
    private String lw;
    private String lwtd;
    private String rtd;
    private String superLabel;
    private String updatedDate;
    private String wtd;
    private List<Link> links;

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }


    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getItunesPreviousRank() {
        return itunesPreviousRank;
    }
    public void setItunesPreviousRank(String itunesPreviousRank) {
        this.itunesPreviousRank = itunesPreviousRank;
    }
    public String getItunesRank() {
        return itunesRank;
    }
    public void setItunesRank(String itunesRank) {
        this.itunesRank = itunesRank;
    }

    public String getLd() {
        return ld;
    }
    public void setLd(String ld) {
        this.ld = ld;
    }
    public String getLw() {
        return lw;
    }
    public void setLw(String lw) {
        this.lw = lw;
    }
    public String getLwtd() {
        return lwtd;
    }
    public void setLwtd(String lwtd) {
        this.lwtd = lwtd;
    }
    public String getRtd() {
        return rtd;
    }
    public void setRtd(String rtd) {
        this.rtd = rtd;
    }
    public String getSuperLabel() {
        return superLabel;
    }
    public void setSuperLabel(String superLabel) {
        this.superLabel = superLabel;
    }
    public String getUpdatedDate() {
        return updatedDate;
    }
    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }
    public String getWtd() {
        return wtd;
    }
    public void setWtd(String wtd) {
        this.wtd = wtd;
    }
}