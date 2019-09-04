package com.uestc.webapi.service.hive.Impl;


import com.uestc.webapi.common.ListResult;
import com.uestc.webapi.common.Result;
import com.uestc.webapi.common.StatEnum;
import com.uestc.webapi.service.hive.HobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * create by fucheng on 2019/09/01
 */

@Service
public class HobbyServiceImpl implements HobbyService {


    @Autowired
    @Qualifier("hiveJdbcTemplate")
    private JdbcTemplate hiveJdbcTemplate;


    @Override
    public List<String> listAllTablesa() {
        List<String> list = new ArrayList<String>();
        List<Map<String, Object>> mapList = hiveJdbcTemplate.queryForList("show tables");
        for (Map<String, Object> map : mapList) {
            for (Map.Entry<String, Object> m : map.entrySet()) {
                list.add(m.getValue().toString());
                System.out.println(m.getValue());
            }
        }
        return list;
    }

    @Override
    public Result getHobbyList() {

        List<Map<String, Object> > hobbyList = new ArrayList();

        List<Map<String, Object>> hobbyMapList = hiveJdbcTemplate.queryForList("select * from hobbies_classify");

        for (Map<String, Object> map : hobbyMapList){
            Map<String,Object> temp = new HashMap<String, Object>();
            temp.put("hobby",map.get("hobbies_classify.interesthobbyname"));
            temp.put("num",map.get("hobbies_classify.count"));
            hobbyList.add(temp);

        }

        return new ListResult(StatEnum.SUCCESS.getState(),StatEnum.SUCCESS.getInfo(),hobbyList.size(),hobbyList);
    }


}
