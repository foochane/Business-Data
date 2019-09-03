package com.uestc.webapi.service.mysql.Impl;


import com.uestc.webapi.common.ListResult;
import com.uestc.webapi.common.Result;
import com.uestc.webapi.common.StatEnum;
import com.uestc.webapi.dao.entity.CustRepo;
import com.uestc.webapi.dao.mapper.CustRepoMap;
import com.uestc.webapi.model.mysql.CustRepoData;
import com.uestc.webapi.service.mysql.CustRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "CustRepoService")
public class CustRepoServiceImpl implements CustRepoService {
    @Autowired
    private CustRepoMap custRepoMap;

    @Override
    public Result getCustRepoList() {
        List<CustRepo> custRepoList = custRepoMap.getCustRepoList();
        List<CustRepoData> custRepoDataList = new ArrayList<>();


        for(CustRepo data : custRepoList){
            CustRepoData temp = new CustRepoData();
            temp.setBillmouth(data.getBillmouth());
            temp.setNum_amt(data.getNum_amt());
            temp.setNum_repo(data.getNum_repo());
            temp.setRepo_rate(data.getRepo_rate());
            custRepoDataList.add(temp);
        }

        return new ListResult(StatEnum.SUCCESS.getState(),StatEnum.SUCCESS.getInfo(),custRepoDataList.size(),custRepoDataList);
    }
}
