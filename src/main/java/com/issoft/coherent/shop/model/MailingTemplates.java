package com.issoft.coherent.shop.model;

public enum MailingTemplates {

    REGISTRATION("registration.html");

    private String path;

    MailingTemplates(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
