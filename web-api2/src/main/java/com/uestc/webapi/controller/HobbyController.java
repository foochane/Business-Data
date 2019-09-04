package com.uestc.webapi.controller;

import com.uestc.webapi.common.Result;
import com.uestc.webapi.model.hive.Hobby;
import com.uestc.webapi.service.hive.HobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * create by fucheng 2019/09/01
 * api/hobby/list
 */
@Controller
@RequestMapping("/api")
public class HobbyController {


    @Autowired
    private HobbyService hiveService;

    @ResponseBody
    @RequestMapping(value = "/tbList", method = RequestMethod.GET)
    public List<String> listAllTablesa2(){
        List<String> tbList = hiveService.listAllTablesa();

        return tbList;
    }

    @ResponseBody
    @RequestMapping(value = "/hobby/list", method = RequestMethod.GET)
    public Result getHobbyList(){
        return hiveService.getHobbyList();
    }


}
