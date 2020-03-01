package com.zhengyuanfang.dto.in;

public class CustomerRegisterInDto {
    private String username;
    private String realname;
    private String mobile;
    private String email;
    private String password;
    private boolean newsSubscribed;

    public CustomerRegisterInDto(String username, String realname, String mobile, String email, String password, boolean newsSubscribed) {
        this.username = username;
        this.realname = realname;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.newsSubscribed = newsSubscribed;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isNewsSubscribed() {
        return newsSubscribed;
    }

    public void setNewsSubscribed(boolean newsSubscribed) {
        this.newsSubscribed = newsSubscribed;
    }
}
