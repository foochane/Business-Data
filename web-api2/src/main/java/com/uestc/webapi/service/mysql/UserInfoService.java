package com.uestc.webapi.service.mysql;


import com.uestc.webapi.common.Result;

/**
 * Created by fucheng on 2019/7/15.
 */
public interface UserInfoService {

    Result login(String account, String password);

    Result getUserList();

}
