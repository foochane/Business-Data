package com.uestc.webapi.dao.mapper;


import com.uestc.webapi.dao.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by fucheng on 2019/7/15.
 */
public interface UserInfoMapper {

    String getPassword(@Param("account") String account);

    List<UserInfo> getUserList();
}
