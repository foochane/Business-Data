package com.uestc.webapi.service.mysql;

import com.uestc.webapi.dao.entity.Account;

import java.util.List;

public interface AccountService {

    /**
     * 查询所有
     */
    List<Account> getAccountList();
}
