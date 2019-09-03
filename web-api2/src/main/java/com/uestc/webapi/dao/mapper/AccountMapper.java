package com.uestc.webapi.dao.mapper;


import com.uestc.webapi.dao.entity.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface AccountMapper {

	/**
	 * 查询所有
	 */
	List<Account> getAccountList();
}
