package com.uestc.webapi.service.hive;



import com.uestc.webapi.model.hive.Hobby;

import java.util.List;

public interface HobbyService {

    /**
     * 查询hive连接库中所有表 select * from hobbies_classify
     * @return
     */
    List<String> listAllTablesa();

    List<Hobby> getHobbyList();
}
