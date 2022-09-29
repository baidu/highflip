package com.baidu.highflip.server.adapter.impl;

import com.baidu.highflip.core.adaptor.UserAdaptor;
import com.baidu.highflip.core.entity.runtime.User;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class FixedSingleUserAdaptor implements UserAdaptor {

    public static final String USER_NAME = "single_tester";

    public static final String USER_ID = "00000001";

    public static final String BIND_USER_ID = "fixed.single.user.adaptor.user.id";

    @Override
    public User createUser(User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public User updateUser(User user) {
        return user;
    }

    @Override
    public void deleteUser(User user) {

        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasUser(User user) {
        String userid = user.getBinding().getOrDefault(BIND_USER_ID, "").toString();
        if (userid.compareTo(USER_ID) == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int getUserCount() {
        return 1;
    }

    @Override
    public User getUserByIndex(int index, User user) {
        if (index == 1) {
            user.setName(USER_NAME);
            user.getBinding().put(BIND_USER_ID, USER_ID);
            return user;
        } else {
            throw new IllegalArgumentException();
        }
    }
}