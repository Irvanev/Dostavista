package com.example.dostavista.models.entities;

public class ActionLink {
    private String href;
    private String method;

    public ActionLink(String href, String method) {
        this.href = href;
        this.method = method;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
