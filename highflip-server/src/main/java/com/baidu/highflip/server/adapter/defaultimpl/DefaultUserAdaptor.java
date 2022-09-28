package com.baidu.highflip.server.adapter.defaultimpl;

import com.baidu.highflip.core.adaptor.UserAdaptor;
import com.baidu.highflip.core.entity.runtime.User;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class DefaultUserAdaptor implements UserAdaptor {

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
        return false;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public User getUserByIndex(int index, User user) {

        return null;
    }
}