package com.hailong.fireshare.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hailong.fireshare.common.RestResult;
import com.hailong.fireshare.entity.User;

public interface UserService extends IService<User> {
    RestResult<String> registerUser(User user);

    RestResult<User> login(User user);

    User getUserByToken(String token);
}
