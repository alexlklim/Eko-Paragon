package com.alex.eko.paragon.email;
public enum EmailType {

    CONFIRM_EMAIL("Email confirmation"),
    LICENSE_ACTIVATION("License activation"),
    LICENSE_EXPIRATION_SOON("License expiration soon"),
    LICENSE_EXPIRATION("License expiration"),
    ANNOUNCEMENT("Announcement"),
    PASSWORD_WAS_CHANGED("Password was changed"),
    FORGOT_PASSWORD("Forgot password");

    private final String subject;

    EmailType(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }
}
