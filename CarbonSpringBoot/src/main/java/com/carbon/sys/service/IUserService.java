package com.carbon.sys.service;

import com.carbon.sys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;
import java.util.Objects;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhang
 * @since 2023-08-10
 */
public interface IUserService extends IService<User> {

    Map<String, Object> login(User user);

    Map<String, Object> getUserInfo(String token);

    void logout(String token);
}
