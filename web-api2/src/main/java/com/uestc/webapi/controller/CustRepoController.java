package com.uestc.webapi.controller;


import com.uestc.webapi.common.Result;
import com.uestc.webapi.service.mysql.CustRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "api")
public class CustRepoController {

    @Autowired
    private CustRepoService custRepoService;

    @RequestMapping(value = "custrepo/list",method = RequestMethod.GET)
    @ResponseBody
    public Result getCustRepoList(){

        return custRepoService.getCustRepoList();
    }
}
