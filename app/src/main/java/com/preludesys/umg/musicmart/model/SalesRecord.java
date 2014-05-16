package com.preludesys.umg.musicmart.model;

import java.util.List;

/**
 * Created by varunsundaramoorthy on 4/11/14.
 */
public class SalesRecord extends AbstractDomain {
    private static final long serialVersionUID = 694379600452602646L;


    private List<Link> links;

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }


    private String artistId;
    private String appleProductId;
    private Integer salesRecordId;
    private String lwtd;
    private String rank;
    private String rtd;
    private String title;
    private String wtd;
    private Integer percentage;
    private String imageUrl;
    private String previewUrl;
    private String label;
    private String configId;
    private String partner;

    public String getArtistId() {
        return artistId;
    }
    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }
    public Integer getSalesRecordId() {
        return salesRecordId;
    }
    public void setSalesRecordId(Integer salesRecordId) {
        this.salesRecordId = salesRecordId;
    }
    public String getLwtd() {
        return lwtd;
    }
    public void setLwtd(String lwtd) {
        this.lwtd = lwtd;
    }
    public String getRank() {
        return rank;
    }
    public void setRank(String rank) {
        this.rank = rank;
    }
    public String getRtd() {
        return rtd;
    }
    public void setRtd(String rtd) {
        this.rtd = rtd;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getWtd() {
        return wtd;
    }
    public void setWtd(String wtd) {
        this.wtd = wtd;
    }
    public Integer getPercentage() {
        return percentage;
    }
    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getConfigId() {
        return configId;
    }
    public void setConfigId(String configId) {
        this.configId = configId;
    }
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public String getPreviewUrl() {
        return previewUrl;
    }
    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }
    public String getAppleProductId() {
        return appleProductId;
    }
    public void setAppleProductId(String appleProductId) {
        this.appleProductId = appleProductId;
    }
    public String getPartner() {
        return partner;
    }
    public void setPartner(String partner) {
        this.partner = partner;
    }

}
