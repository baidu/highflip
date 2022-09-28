package com.baidu.highflip.server.adapter.defaultimpl;

import com.baidu.highflip.core.adaptor.UserAdaptor;
import com.baidu.highflip.core.entity.runtime.User;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class DefaultUserAdaptor implements UserAdaptor {

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public boolean hasUser(User user) {
        return false;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public User getUserByIndex(int index, User user) {
        return null;
    }
}