package com.denshiksmle.githubee.presentation.entities;

import java.util.Objects;

public class UserItem {

    private String userName;
    private String avatarUrl;

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserItem userItem = (UserItem) o;

        if (!Objects.equals(userName, userItem.userName))
            return false;
        return Objects.equals(avatarUrl, userItem.avatarUrl);
    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (avatarUrl != null ? avatarUrl.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserItem{" +
                "userName='" + userName + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }
}
