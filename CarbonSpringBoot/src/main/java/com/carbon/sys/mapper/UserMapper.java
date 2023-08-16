package com.carbon.sys.mapper;

import com.carbon.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhang
 * @since 2023-08-10
 */
public interface UserMapper extends BaseMapper<User> {
        public List<String> getRoleByUserId(Integer userId);
}
