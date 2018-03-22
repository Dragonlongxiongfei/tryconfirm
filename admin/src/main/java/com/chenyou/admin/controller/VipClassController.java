package com.chenyou.admin.controller;

import com.chenyou.admin.Utils.ApplicationConstant;
import com.chenyou.admin.Utils.StringTools;
import com.chenyou.admin.Utils.ToolUtil;
import com.chenyou.admin.domain.VipClass;
import com.chenyou.admin.service.VipClassService;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * Created by Shell Li on 2017/12/11.
 */
@Controller
@RequestMapping(value = "vip")
public class VipClassController {
    final static Logger log = LoggerFactory.getLogger(VipClassController.class);

    @Autowired
    private VipClassService vipClassService;

    @RequestMapping(value = "/buyerVipItems", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView initPage(){
        ModelAndView mv = new ModelAndView("buyervipitems");
        return mv;
    }

    @RequestMapping(value = "/buyerVipItemsData", method = {RequestMethod.GET})
    @ResponseBody
    public String buyerVipItems(){
        String result = "";
        try {
            List<VipClass> vipItems = vipClassService.getAll();
            result = ToolUtil.tableFormat(vipItems, 1);
        } catch (Exception e) {
            log.error("init page error", e);
        }
        return result;
    }

    @RequestMapping(value = "/dataSave", method = {RequestMethod.POST})
    public String dataSave(HttpServletRequest request, @RequestParam("vipIcon") MultipartFile file){
        try {
        String vipId = request.getParameter("vipId"), vipTitle = request.getParameter("vipTitle"),
            vipType = request.getParameter("vipType"), vipPrice = request.getParameter("vipPrice"),
            basicPrice = request.getParameter("basicPrice"), discount = request.getParameter("discount"),
            effectiveTime = request.getParameter("effectiveTime"),
            twiceFlag = request.getParameter("twiceFlag"), threeTimeFlag = request.getParameter("threeTimeFlag"),
            increaseTenFlag = request.getParameter("increaseTenFlag"), increaseTwentyFlag = request.getParameter("increaseTwentyFlag"),
            goodsVipOnly = request.getParameter("goodsVipOnly"), birthdayPrize = request.getParameter("birthdayPrize"),
            bigBirthdayPrize = request.getParameter("bigBirthdayPrize"), priorityPass = request.getParameter("priorityPass"),
            customService = request.getParameter("customService"), invalidFlag = request.getParameter("invalidFlag");

            // save as local file
            String storeDirectory = ApplicationConstant.CDN_URL + StringTools.dateToString(new Date(), "yyyyMMdd");
            String original1 = file.getOriginalFilename();
            int index = original1.lastIndexOf(".");
            String extend1 = original1.substring(index);
            String filePath = new Date().getTime() + RandomUtils.nextInt(10000) + extend1;
            File directory = new File(storeDirectory);
            if (!directory.exists())
                directory.mkdirs();
            if (!file.isEmpty()) {
                File saveAsFile = new File(storeDirectory + "/" + filePath);
                file.transferTo(saveAsFile);
            }

            twiceFlag = StringUtils.defaultIfEmpty(twiceFlag, "0");
            threeTimeFlag = StringUtils.defaultIfEmpty(threeTimeFlag, "0");
            increaseTenFlag = StringUtils.defaultIfEmpty(increaseTenFlag, "0");
            increaseTwentyFlag = StringUtils.defaultIfEmpty(increaseTwentyFlag, "0");
            goodsVipOnly = StringUtils.defaultIfEmpty(goodsVipOnly, "0");
            birthdayPrize = StringUtils.defaultIfEmpty(birthdayPrize, "0");
            bigBirthdayPrize = StringUtils.defaultIfEmpty(bigBirthdayPrize, "0");
            priorityPass = StringUtils.defaultIfEmpty(priorityPass, "0");
            customService = StringUtils.defaultIfEmpty(customService, "0");

            // setters
            VipClass vipClass = null;
            if (StringUtils.isEmpty(vipId)) {
                vipClass = new VipClass();
            } else {
                vipClass = vipClassService.getSingle(Long.valueOf(vipId));
            }
            vipClass.setVipTitle(vipTitle);
            vipClass.setVipType(Integer.valueOf(vipType));
            vipClass.setVipPrice(Float.valueOf(vipPrice));
            vipClass.setBasicPrice(Float.valueOf(basicPrice));
            vipClass.setDiscount(Float.valueOf(discount));
            vipClass.setEffectiveTime(Integer.valueOf(effectiveTime));
            vipClass.setInvalidFlag(Integer.valueOf(invalidFlag));
            vipClass.setVipIcon(ApplicationConstant.IMG_URL + "/" + StringTools.dateToString(new Date(), "yyyyMMdd") + "/" + filePath);
            vipClass.setTwiceFlag(Integer.parseInt(twiceFlag));
            vipClass.setThreeTimeFlag(Integer.parseInt(threeTimeFlag));
            vipClass.setIncreaseTenFlag(Integer.parseInt(increaseTenFlag));
            vipClass.setIncreaseTwentyFlag(Integer.parseInt(increaseTwentyFlag));
            vipClass.setGoodsVipOnly(Integer.parseInt(goodsVipOnly));
            vipClass.setBirthdayPrize(Integer.parseInt(birthdayPrize));
            vipClass.setBigBirthdayPrize(Integer.parseInt(bigBirthdayPrize));
            vipClass.setPriorityPass(Integer.parseInt(priorityPass));
            vipClass.setCustomService(Integer.parseInt(customService));

            if (vipClass.getVipId() == 0L) {
                vipClassService.saveOne(vipClass);
            } else {
                vipClassService.editOne(vipClass);
            }
        } catch (Exception e) {
            log.error("dataSave error", e);
        }
        return "redirect:/vip/buyerVipItems";
    }
}
