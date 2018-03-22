package com.chenyou.admin.controller;

import com.chenyou.admin.Utils.ToolUtil;
import com.chenyou.admin.domain.BuyPlatform;
import com.chenyou.admin.service.BuyPlatformService;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Shell Li on 2017/12/12.
 */
@Controller
@RequestMapping(value = "/buy_platform")
public class BuyPlatformController {
    final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private BuyPlatformService buyPlatformService;

    @RequestMapping(value = "/init", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView initPage(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("platformclass");
        return mv;
    }

    @RequestMapping(value = "/data_save", method = {RequestMethod.POST})
    public String datasave(HttpServletRequest request){
        try {
            buyPlatformService.save(request);
        } catch (Exception e) {
            log.error("datasave error", e);
        }
        return "redirect:/buy_platform/init";
    }

    @RequestMapping(value = "/data_list", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String dataList(HttpServletRequest request){
        String result;
        List<BuyPlatform> list = buyPlatformService.getAll();
        result = ToolUtil.tableFormat(list, 1);
        return result;
    }

}
