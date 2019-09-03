package com.uestc.webapi.controller;


import com.uestc.webapi.common.Result;
import com.uestc.webapi.service.mysql.MemberInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by fucheng on 2019/7/15.
 */

@Controller
@RequestMapping(value = "api")
public class MemberInfoController {

    @Autowired
    private MemberInfoService memberInfoService;

    @RequestMapping(value = "member/list",method = RequestMethod.GET)
    @ResponseBody
    public Result getMemberList(){

        return memberInfoService.getMemberList();
    }
}
