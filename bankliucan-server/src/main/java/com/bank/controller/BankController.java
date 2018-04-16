package com.bank.controller;

import com.alibaba.fastjson.JSON;
import com.bank.pojo.AccountUser;
import com.bank.pojo.Bank;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//采用注解的方式，可以明确地定义该类为处理请求的Controller类
@Controller
@RequestMapping("/bank")
public class BankController {
    private Bank bank = new Bank();

    //查询余额
    @RequestMapping(value = "/queryMoney",method = RequestMethod.GET)
    public void queryMoney(HttpServletRequest request, HttpServletResponse response) {
        int money = bank.getMoney(Integer.valueOf(request.getParameter("accountId")));
        String msg = (money >= 0 ? "sucess" : "fail");

        Map<String,Object> mapResult = new HashMap<>();
        mapResult.put("result",msg);
        mapResult.put("money",money);
        String resJSON = JSON.toJSONString(mapResult);
        try {
            response.getOutputStream().write(resJSON.getBytes("UTF-8"));
            response.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeResponseBase(response);
    }

    //查询所有用户
    @RequestMapping(value = "/serachAll",method = RequestMethod.GET)
    public void serachAll(HttpServletResponse response) {
        Map<String,Object> mapResult = new HashMap<>();
        mapResult.put("result", "sucess");

        List<Map<String,Object>> maps = new ArrayList<Map<String,Object>>();
        List<AccountUser> users = bank.getAccounts();
        for (AccountUser user : users) {
            Map<String,Object> map = new HashMap<>();
            map.put("accountId", user.getAccountId());
            map.put("uid", user.getUid());
            map.put("money", user.getMoney());
            map.put("sex", user.getSex());
            map.put("name", user.getName());
            maps.add(map);
        }
        mapResult.put("msg", maps);

        String resJSON = JSON.toJSONString(mapResult);
        try {
            response.getOutputStream().write(resJSON.getBytes("UTF-8"));
            response.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeResponseBase(response);
    }

    //存钱
    @RequestMapping(value = "/saveMoney",method = RequestMethod.POST)
    public void saveMoney(HttpServletRequest request, HttpServletResponse response) {
        int accountId = Integer.valueOf(request.getParameter("accountId"));
        int money = Integer.valueOf(request.getParameter("money"));
        boolean result = bank.saveMoney(accountId, money);

        String msg = (result ? "sucess" : "fail");

        Map<String,Object> mapResult = new HashMap<>();
        mapResult.put("result",msg);
        String resJSON = JSON.toJSONString(mapResult);
        try {
            response.getOutputStream().write(resJSON.getBytes("UTF-8"));
            response.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeResponseBase(response);
    }

    //取钱
    @RequestMapping(value = "/takeMoney",method = RequestMethod.POST)
    public void takeMoney(HttpServletRequest request, HttpServletResponse response) {
        int accountId = Integer.valueOf(request.getParameter("accountId"));
        int money = Integer.valueOf(request.getParameter("money"));
        boolean result = bank.takeMoney(accountId, money);
        String msg = (result ? "sucess" : "fail");

        Map<String,Object> mapResult = new HashMap<>();
        mapResult.put("result",msg);
        String resJSON = JSON.toJSONString(mapResult);
        try {
            response.getOutputStream().write(resJSON.getBytes("UTF-8"));
            response.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeResponseBase(response);
    }

    //转账
    @RequestMapping(value = "/transferMoney",method = RequestMethod.POST)
    public void transferMoney(HttpServletRequest request, HttpServletResponse response) {
        int accountFromId = Integer.valueOf(request.getParameter("accountFromId"));
        int accountToIdId = Integer.valueOf(request.getParameter("accountToId"));
        int money = Integer.valueOf(request.getParameter("money"));

        boolean result = bank.transferMoney(accountFromId, accountToIdId, money);
        String msg = (result ? "sucess" : "fail");

        Map<String,Object> mapResult = new HashMap<>();
        mapResult.put("result",msg);
        String resJSON = JSON.toJSONString(mapResult);
        try {
            response.getOutputStream().write(resJSON.getBytes("UTF-8"));
            response.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeResponseBase(response);
    }

    private void writeResponseBase(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.setStatus(200);
    }
}
