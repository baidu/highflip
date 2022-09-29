package com.baidu.highflip.core.adaptor;

import com.baidu.highflip.core.entity.runtime.User;

public interface UserAdaptor {

    User createUser(User user);

    User updateUser(User user);

    void deleteUser(User user);

    boolean hasUser(User user);

    int getUserCount();

    User getUserByIndex(int index, User user);

}
