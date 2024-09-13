package com.alex.eko.paragon.old.emails;


import lombok.Getter;

@Getter
public enum EmailType {

    CONFIRM_EMAIL("e-Paragon Email confirmation"),
    ANNOUNCEMENT("e-Paragon Announcement"),
    PASSWORD_WAS_CHANGED("e-Paragon Password was changed"),
    FORGOT_PASSWORD("e-Paragon Forgot password");

    private final String subject;

    EmailType(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }
}
