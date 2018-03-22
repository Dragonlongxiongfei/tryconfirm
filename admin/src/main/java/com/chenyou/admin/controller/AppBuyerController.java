package com.chenyou.admin.controller;

import com.chenyou.admin.domain.SysUserBuyer;
import com.chenyou.admin.service.BuyerService;
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
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Shell Li on 2017/11/29.
 */
@Controller
@RequestMapping("/user")
public class AppBuyerController {

    final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BuyerService buyerService;

    @RequestMapping(value = "/buyerlist", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView initPage(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("buyerlist");
        String uid = request.getParameter("uid"), qq = request.getParameter("qq"),
                realName = request.getParameter("real-name"), userAccount = request.getParameter("user-account"), inuid = request.getParameter("inuid"),
                wwID = request.getParameter("ww-id"), withInvalid = request.getParameter("with-invalid");
        String[] sexs = request.getParameterValues("sex");
        String sex = null;
        if (sexs != null){
             sex = Arrays.asList(sexs).toString().replace("[","(").replace("]", ")");
             if (sex.contains("男"))
                 sex = sex.replace("男","'男'");
             if (sex.contains("女"))
                 sex = sex.replace("女", "'女'");
        }

        mav.addObject("uid",StringUtils.defaultIfEmpty(uid, ""));
        mav.addObject("inuid",StringUtils.defaultIfEmpty(inuid, ""));
        mav.addObject("qq",StringUtils.defaultIfEmpty(qq, ""));
        mav.addObject("realName",StringUtils.defaultIfEmpty(realName, ""));
        mav.addObject("userAccount",StringUtils.defaultIfEmpty(userAccount, ""));
        mav.addObject("wwID",StringUtils.defaultIfEmpty(wwID, ""));
        mav.addObject("sex", sex);
        mav.addObject("withInvalid",StringUtils.defaultIfEmpty(withInvalid, ""));
        return mav;
    }

    @RequestMapping(value = "/datalist", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String datalist(HttpServletRequest request,@RequestParam(defaultValue = "0", required = false) int page,
                           @RequestParam(defaultValue = "50", required = false) int size){
        String result = null;
        try {
            result = buyerService.searchBuyers(request, page, size);
        } catch(Exception  e) {
            JSONObject msg = new JSONObject();
            msg.put("error", e.getMessage());
            result = msg.toString();
            LOG.error("datalist error", e);
        }

        return result;
    }


    @RequestMapping(value = "/buyerinfo", method = {RequestMethod.GET})
    public ModelAndView getOneBuyer(@RequestParam("uid") String uid){
        ModelAndView mav = new ModelAndView("buyerinfo");
        try {
            SysUserBuyer sysUserBuyer = buyerService.findOneBuyer(Long.valueOf(uid));
            mav.addObject("sysUserBuyer", sysUserBuyer);
        } catch (Exception e) {
            JSONObject msg = new JSONObject();
            msg.put("error", e.getMessage());
            LOG.error("datalist error", e);
        }

        return mav;
    }

    @RequestMapping(value = "/datasave", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String datasave(HttpServletRequest request){
        String result = "操作成功";
        try {
            String forbiddenTime = request.getParameter("forbiddenTime"),invalidFlag = request.getParameter("invalidFlag");
            long uid = Long.valueOf(request.getParameter("uid"));
            SysUserBuyer sysUserBuyer = buyerService.findOneBuyer(uid);
            if (!StringUtils.isEmpty(forbiddenTime)) {
                sysUserBuyer.setForbiddenTime(forbiddenTime);
            }
            if (!StringUtils.isEmpty(invalidFlag)) {
                sysUserBuyer.setInvalidFlag(Integer.valueOf(invalidFlag));
            }
            buyerService.saveBuyer(sysUserBuyer);
        } catch (Exception e) {
            LOG.error("save error", e);
            JSONObject msg = new JSONObject();
            msg.put("error", e.getMessage());
            result = msg.toString();
        }
        return result;
    }

    @RequestMapping(value = "/buyerLimit", method = {RequestMethod.POST})
    public String userLimit(HttpServletRequest request) {
        try {
            String forbiddenTime = request.getParameter("forbiddenTime"), forbiddenReason = request.getParameter("forbiddenReason");
            String uid = request.getParameter("uid");

            SysUserBuyer buyer = buyerService.findOneBuyer(Long.valueOf(uid));
            if (buyer != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, Integer.valueOf(forbiddenTime));
                Date dateUntil = calendar.getTime();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String forbiddenDate = sdf.format(dateUntil);
                buyer.setForbiddenTime(forbiddenDate);
                buyer.setForbiddenReason(forbiddenReason);
                buyer.setInvalidFlag(0);
                buyerService.saveBuyer(buyer);
            }
        } catch (Exception e) {
            LOG.error("buyerLimit error: ", e);
        }
        return "redirect:/user/buyerlist";
    }

}
