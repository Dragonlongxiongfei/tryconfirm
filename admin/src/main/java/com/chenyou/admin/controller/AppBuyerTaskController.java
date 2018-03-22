package com.chenyou.admin.controller;

import com.chenyou.admin.domain.BuyerTaskVO;
import com.chenyou.admin.service.BuyerTaskService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by Shell Li on 2017/12/4.
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/user")
public class AppBuyerTaskController {
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BuyerTaskService buyerTaskService;

    @RequestMapping(value = "/buyerTaskList", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView initPage(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("buyertasklist");
        String uid = request.getParameter("uid"), userAccount = request.getParameter("userAccount");
        mav.addObject("uid", StringUtils.defaultIfEmpty(uid, ""));
        mav.addObject("userAccount", StringUtils.defaultIfEmpty(userAccount, ""));

        return mav;
    }

    @RequestMapping(value = "/taskList", method = {RequestMethod.GET})
    public String datalist(HttpServletRequest request, @RequestParam(defaultValue = "0", required = false) int page,
                           @RequestParam(defaultValue = "50", required = false) int size){
        String result = "系统繁忙";
        try {
            result = buyerTaskService.searchTasks(request, page, size).toString();
        } catch (Exception e) {
            log.error("data list error", e);
        }
        return result;
    }

    @RequestMapping(value = "/taskInfo", method = {RequestMethod.GET})
    public ModelAndView taskInfo(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("taskinfo");
        try {
            String taskID = request.getParameter("taskID");
            BuyerTaskVO buyerTaskVO = buyerTaskService.getOne(taskID);
            mav.addObject("buyerTaskVO", buyerTaskVO);
        } catch (Exception e) {
            log.error("get taskID error", e);
            mav.addObject("errorMsg","数据异常");
        }

        return mav;
    }



}
