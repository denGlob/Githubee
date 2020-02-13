package com.denshiksmle.githubee.presentation.entities;

import com.denshiksmle.githubee.domain.entities.DetailUser;
import com.denshiksmle.githubee.domain.entities.User;

import java.util.ArrayList;
import java.util.List;

public class Mappers {

    public static UserItem fromUser(final User user) {
        if(user == null) {
            return null;
        }

        final UserItem userItem = new UserItem();

        userItem.setAvatarUrl(user.getAvatarUrl());
        userItem.setUserName(user.getLogin());

        return userItem;
    }

    public static List<UserItem> fromUsers(final List<User> users) {
        final List<UserItem> userLists = new ArrayList<>(users.size());
        for (final User user : users) {
            userLists.add(fromUser(user));
        }
        return userLists;
    }

    public static UserDetailInfo fromDetailUser(final DetailUser detailUser) {
        if (detailUser == null) {
            return null;
        }

        final UserDetailInfo userDetailInfo = new UserDetailInfo();

        userDetailInfo.setAvatarUrl(detailUser.getAvatarUrl());
        userDetailInfo.setUserName(detailUser.getName());
        userDetailInfo.setUserRepos(detailUser.getPublicReposCount());
        userDetailInfo.setUserGists(detailUser.getPublicGistsCount());
        userDetailInfo.setFollowers(detailUser.getFollowersCount());
        userDetailInfo.setUserUrl(detailUser.getUrl());

        return userDetailInfo;
    }
}
