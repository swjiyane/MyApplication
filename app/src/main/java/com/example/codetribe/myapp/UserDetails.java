package com.example.codetribe.myapp;

/**
 * Created by CodeTribe on 10/18/2017.
 */

public class UserDetails {

    private String mUser;
    private String mEmail;
    private String mPassword;

    public String getmUser() {
        return mUser;
    }

    public void setmUser(String mUser) {
        this.mUser = mUser;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public UserDetails(String mUser, String mEmail, String mPassword) {
        this.mUser = mUser;
        this.mEmail = mEmail;
        this.mPassword = mPassword;

    }

    public UserDetails() {
    }
}
