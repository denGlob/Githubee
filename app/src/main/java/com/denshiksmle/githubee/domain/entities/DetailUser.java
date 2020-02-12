package com.denshiksmle.githubee.domain.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DetailUser implements Serializable {
    @SerializedName("id") // We can use @Expose annotation insteadof @SerializedName :))
    private String id;
    @SerializedName("login")
    private String login;
    @SerializedName("avatar_url")
    private String avatarUrl;
    @SerializedName("name")
    private String name;
    @SerializedName("url")
    private String url;
    @SerializedName("repos_url")
    private String reposUrl;
    @SerializedName("public_repos")
    private int publicReposCount;
    @SerializedName("gists_url")
    private String gistsUrl;
    @SerializedName("public_gists")
    private int publicGistsCount;
    @SerializedName("followers")
    private int followersCount;

    public String getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getReposUrl() {
        return reposUrl;
    }

    public int getPublicReposCount() {
        return publicReposCount;
    }

    public String getGistsUrl() {
        return gistsUrl;
    }

    public int getPublicGistsCount() {
        return publicGistsCount;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    @Override
    public String toString() {
        return "DetailUser{" +
                "id='" + id + '\'' +
                ", login='" + login + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", reposUrl='" + reposUrl + '\'' +
                ", publicReposCount=" + publicReposCount +
                ", gistsUrl='" + gistsUrl + '\'' +
                ", publicGistsCount=" + publicGistsCount +
                ", followersCount=" + followersCount +
                '}';
    }
}
