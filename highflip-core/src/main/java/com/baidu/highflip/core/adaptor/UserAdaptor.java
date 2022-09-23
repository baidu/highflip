package com.baidu.highflip.core.adaptor;

import com.baidu.highflip.core.entity.runtime.User;

public interface UserAdaptor {

    User createUser(User user);

    void deleteUser(User user);

    boolean hasUser(User user);

    int getCount();

    User getUserByIndex(int index, User user);
}
