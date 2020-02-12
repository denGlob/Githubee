package com.denshiksmle.githubee.presentation.entities;

import java.util.Objects;

public class UserDetailInfo {

    private String userName;
    private String avatarUrl;
    private String userUrl;

    private int userRepos;
    private int userGists;
    private int followers;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }

    public String getUserRepos() {
        return "Repos: " + userRepos;
    }

    public void setUserRepos(int userRepos) {
        this.userRepos = userRepos;
    }

    public String getUserGists() {
        return "Gists: " + userGists;
    }

    public void setUserGists(int userGists) {
        this.userGists = userGists;
    }

    public String getFollowers() {
        return "Followers: " + followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDetailInfo that = (UserDetailInfo) o;

        if (userRepos != that.userRepos) return false;
        if (userGists != that.userGists) return false;
        if (followers != that.followers) return false;
        if (!Objects.equals(userName, that.userName))
            return false;
        if (!Objects.equals(avatarUrl, that.avatarUrl))
            return false;
        return Objects.equals(userUrl, that.userUrl);
    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (avatarUrl != null ? avatarUrl.hashCode() : 0);
        result = 31 * result + (userUrl != null ? userUrl.hashCode() : 0);
        result = 31 * result + userRepos;
        result = 31 * result + userGists;
        result = 31 * result + followers;
        return result;
    }

    @Override
    public String toString() {
        return "UserDetailInfo{" +
                "userName='" + userName + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", userUrl='" + userUrl + '\'' +
                ", userRepos=" + userRepos +
                ", userGists=" + userGists +
                ", followers=" + followers +
                '}';
    }
}
