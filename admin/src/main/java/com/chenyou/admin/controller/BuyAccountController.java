package com.chenyou.admin.controller;

import com.chenyou.admin.service.BuyAccountService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Shell Li on 2017/12/13.
 */
@Controller
@RequestMapping(value = "/buy_account")
public class BuyAccountController {
    final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private BuyAccountService service;

    @RequestMapping(value = "/init", method = {RequestMethod.GET})
    public ModelAndView initPage(){
        ModelAndView mv = new ModelAndView("buyaccount");

        return mv;
    }

    @RequestMapping(value = "/data_list", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String datalist(HttpServletRequest request, @RequestParam(defaultValue = "0", required = false) int page,
                           @RequestParam(defaultValue = "50", required = false) int size){
        String result = "";
        try {
            result = service.datalist(request, page, size);
        } catch (Exception e) {
            log.error("RequestMapping: /buy_account/datalist error", e);
        }
        return result;
    }

    @RequestMapping(value = "/approve_by_id", method = RequestMethod.POST)
    public String datasave(HttpServletRequest request) {
        try {
            String id = request.getParameter("id"), bstatus = request.getParameter("bstatus"),
                comment = request.getParameter("comment"), refuseComment = request.getParameter("refuseComment");
            service.approveById(Long.valueOf(id), Integer.valueOf(bstatus), comment, refuseComment);
        } catch (Exception e) {
            log.error("approve_by_id", e);
        }
        return "redirect:/buy_account/init";
    }


}
