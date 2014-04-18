package com.preludesys.umg.musicmart.model;

/**
 * Created by varunsundaramoorthy on 4/17/14.
 */
public class Link extends AbstractDomain{
    private static final long serialVersionUID = 694379600452602646L;

    private String rel;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    private String href;

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }
}