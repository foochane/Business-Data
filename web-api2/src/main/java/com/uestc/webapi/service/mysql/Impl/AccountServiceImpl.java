package com.uestc.webapi.service.mysql.Impl;

import com.uestc.webapi.dao.entity.Account;
import com.uestc.webapi.dao.mapper.AccountMapper;
import com.uestc.webapi.service.mysql.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;


    @Override
    public List<Account> getAccountList() {
        return accountMapper.getAccountList();
    }
}
