package com.chenyou.admin.controller;

import com.chenyou.admin.domain.Coup;
import com.chenyou.admin.service.CoupService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Shell Li on 2017/12/16.
 */
@Controller
@RequestMapping(value = "coup_setting")
public class AppCounController {
    final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CoupService service;

    @RequestMapping(value = "/init", method = {RequestMethod.GET})
    public ModelAndView initPage(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("couplist");

        return mv;
    }

    @RequestMapping(value = "/data_list", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String datalist(HttpServletRequest request, @RequestParam(defaultValue = "0", required = false) int page,
           @RequestParam(defaultValue = "50", required = false) int size){
        String result = "";
        try {
            result = service.getListService(request, page, size);
        } catch (Exception e){
            log.error("function: datalist error:", e);
        }
        return result;
    }

    @RequestMapping(value = "/data_save", method = {RequestMethod.POST})
    public String datasave(HttpServletRequest request){
        try {
            String id = request.getParameter("id"), coupTitle = request.getParameter("coupTitle"),
                    coupMoney = request.getParameter("coupMoney"), coupNum = request.getParameter("coupNum"),
                    coupUsed = request.getParameter("coupUsed"), coupCondition = request.getParameter("coupCondition"),
                    coupTimeLimit = request.getParameter("coupTimeLimit"), coupDesc = request.getParameter("coupDesc"),
                    invalidFlag = request.getParameter("invalidFlag");
            Coup coup = null;
            if (StringUtils.isEmpty(id)){
                coup = new Coup();
            } else {
                coup = service.getOne(Long.valueOf(id));
            }
            coup.setCoupTitle(coupTitle);
            coup.setCoupMoney(Float.valueOf(coupMoney));
            coup.setCoupNum(Integer.valueOf(coupNum));
            coup.setCoupUsed(Integer.valueOf(coupUsed));
            coup.setCoupCondition(Integer.valueOf(coupCondition));
            coup.setCoupTimeLimit(Integer.valueOf(coupTimeLimit));
            coup.setCoupDesc(coupDesc);
            coup.setInvalidFlag(Integer.valueOf(invalidFlag));
            if (coup.getId() == 0L) {
                service.addCoup(coup);
            } else {
                service.editCoup(coup);
            }
        } catch (Exception e){
            log.error("datasave error", e);
        }
        return "redirect:/coup_setting/init";
    }

    @RequestMapping(value = "/send_coup", method = {RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> sendCoup(String coupID, String sendGroup, String sendPerson, String comment2) {
        Map<String, Object> result = new HashMap<>();
        result.put("msg", "操作成功!");
        try {
            result = service.sendCoups(coupID, sendGroup, sendPerson, comment2);
        } catch (Exception e){
            log.error("sendCoup error:", e);
            result.put("msg", "系统异常");
        }
        return result;
    }

}
