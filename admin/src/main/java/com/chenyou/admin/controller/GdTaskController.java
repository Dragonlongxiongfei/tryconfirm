package com.chenyou.admin.controller;

import com.chenyou.admin.service.GdTaskService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by amoslong on 2018/1/19.
 */
@Controller
@RequestMapping("comment")
public class GdTaskController {
    final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private GdTaskService gdTaskService;

    @RequestMapping(value = "list",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String getGdTaskList(HttpServletRequest request, @RequestParam(defaultValue = "0", required = false) int page,
                                      @RequestParam(defaultValue = "50", required = false) int size){
        String result = null;
        try {
            result = gdTaskService.getList(request, page, size);
        } catch(Exception  e) {
            JSONObject msg = new JSONObject();
            msg.put("error", e.getMessage());
            result = msg.toString();
            LOG.error("datalist error", e);
        }
        return result;
    }
    @RequestMapping(value = "approvelist",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView approvelist(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("seller/comment");
        String uid = request.getParameter("uid"), qq = request.getParameter("qq"),
                realName = request.getParameter("real-name"), userAccount = request.getParameter("user-account"),
                wwID = request.getParameter("ww-id"), withInvalid = request.getParameter("with-invalid");
        mav.addObject("uid", StringUtils.defaultIfEmpty(uid, ""));
        mav.addObject("qq",StringUtils.defaultIfEmpty(qq, ""));
        mav.addObject("realName",StringUtils.defaultIfEmpty(realName, ""));
        mav.addObject("userAccount",StringUtils.defaultIfEmpty(userAccount, ""));
        mav.addObject("wwID",StringUtils.defaultIfEmpty(wwID, ""));
        mav.addObject("withInvalid",StringUtils.defaultIfEmpty(withInvalid, ""));
        return mav;
    }


    @RequestMapping(value = "/datasave", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String datasave(HttpServletRequest request){
        String result = "操作成功";
        try {
            String taskId = request.getParameter("taskId"),sellerId = request.getParameter("sellerId"),buyerId = request.getParameter("buyerId");
            boolean passFlag = Boolean.parseBoolean(request.getParameter("passFlag"));
            gdTaskService.approveTask(taskId,sellerId,buyerId,passFlag);
        } catch (Exception e) {
            LOG.error("save error", e);
            JSONObject msg = new JSONObject();
            msg.put("error", e.getMessage());
            result = msg.toString();
        }
        return result;
    }
}
