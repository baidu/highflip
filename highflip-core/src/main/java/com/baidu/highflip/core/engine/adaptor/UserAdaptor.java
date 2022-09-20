package com.baidu.highflip.core.engine.adaptor;

import com.baidu.highflip.core.entity.runtime.User;

public interface UserAdaptor {

    User createUser(User user);

    void deleteUser(User user);

    int getCount();

    User getUserByIndex(int index);
}
