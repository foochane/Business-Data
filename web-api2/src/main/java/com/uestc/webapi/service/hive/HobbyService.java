package com.uestc.webapi.service.hive;

import com.uestc.webapi.common.Result;
import java.util.List;

/**
 *  create by fucheng on 2019/09/01
 */
public interface HobbyService {

    List<String> listAllTablesa();

    Result getHobbyList();
}
