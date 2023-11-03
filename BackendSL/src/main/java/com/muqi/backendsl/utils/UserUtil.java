package com.muqi.backendsl.utils;

import com.muqi.backendsl.entity.User;

// TODO:
public class UserUtil {

    /** get safe User */

    public static User getSafeUser(User originUser) {
        User safeUser = new User();
        safeUser.setId(originUser.getId());
        safeUser.setUserName(originUser.getUserName());
        safeUser.setAvatarURL(originUser.getAvatarURL());
        safeUser.setLoginAccount(originUser.getLoginAccount());
        safeUser.setUserPhone(originUser.getUserPhone());
        safeUser.setUserEmail(originUser.getUserEmail());
        safeUser.setUserAuthority(originUser.getUserAuthority());
        safeUser.setToken(originUser.getToken());
        return safeUser;
    }
}

