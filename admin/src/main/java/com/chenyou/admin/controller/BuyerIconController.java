package com.chenyou.admin.controller;

import com.chenyou.admin.Application;
import com.chenyou.admin.Utils.ApplicationConstant;
import com.chenyou.admin.Utils.StringTools;
import com.chenyou.admin.domain.BuyerIcon;
import com.chenyou.admin.service.BuyerIconService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.omg.PortableInterceptor.INACTIVE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;

/**
 * Created by Shell Li on 2017/12/18.
 */
@Controller
@RequestMapping(value = "buyer_icon")
public class BuyerIconController {

    final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BuyerIconService service;

    @RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("buyiconsetting");

        return mv;
    }

    @RequestMapping(value = "/data_list", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String datelist(HttpServletRequest request, @RequestParam(defaultValue = "0", required = false) int page,
                           @RequestParam(defaultValue = "50", required = false) int size){
        String result = null;
        try {
            result = service.getDataList(request, page, size);
        } catch (Exception e) {
            log.error("datalist error: ", e);
        }
        return result;
    }

    @RequestMapping(value = "/data_save", method = {RequestMethod.POST})
    public String dataSave(HttpServletRequest request, @RequestParam("lightImg") MultipartFile file1,
                           @RequestParam("darkImg") MultipartFile file2){
        try {
            String id = request.getParameter("id"), iconTitle = request.getParameter("iconTitle"),
                    comment = request.getParameter("comment"), invalidFlag = request.getParameter("invalidFlag");
            // 保存图片
            String storeDirectory = ApplicationConstant.CDN_URL + StringTools.dateToString(new Date(), "yyyyMMdd");
            String original1 = file1.getOriginalFilename();
            int index = original1.lastIndexOf(".");
            String extend1 = original1.substring(index);
            String original2 = file2.getOriginalFilename();
            int index2 = original2.lastIndexOf(".");
            String extend2 = original2.substring(index2);
            String path1 = new Date().getTime() + RandomUtils.nextInt(10000) + extend1;
            String path2 = new Date().getTime() + RandomUtils.nextInt(10000) + extend2;
            File directory = new File(storeDirectory);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            if (!file1.isEmpty()) {
                File lightImgFile = new File(storeDirectory + "/" + path1);
                file1.transferTo(lightImgFile);
            }
            if (!file2.isEmpty()) {
                File darkImgFile = new File(storeDirectory + "/" + path2);
                file2.transferTo(darkImgFile);
            }

            BuyerIcon buyerIcon = null;
            if (StringUtils.isEmpty(id)) {
                buyerIcon = new BuyerIcon();
            } else {
                buyerIcon = service.getOne(Long.valueOf(id));
            }
            if (!StringUtils.isEmpty(iconTitle)){
                buyerIcon.setIconTitle(iconTitle.trim());
            }
            if (!StringUtils.isEmpty(path1)) {
                buyerIcon.setLightImg(ApplicationConstant.IMG_URL + "/" + StringTools.dateToString(new Date(), "yyyyMMdd") + "/" + path1.trim());
            }
            if (!StringUtils.isEmpty(path2)) {
                buyerIcon.setDarkImg(ApplicationConstant.IMG_URL + "/" + StringTools.dateToString(new Date(), "yyyyMMdd") + "/" + path2);
            }
            if (!StringUtils.isEmpty(comment)) {
                buyerIcon.setComment(comment);
            }
            if (!StringUtils.isEmpty(invalidFlag)) {
                buyerIcon.setInvalidFlag(Integer.valueOf(invalidFlag));
            }

            if (buyerIcon.getId() == 0L) {
                service.addOne(buyerIcon);
            } else {
                service.editOne(buyerIcon);
            }
        } catch (Exception e){
            log.error("data save error: ", e);
        }
        return "redirect:/buyer_icon/init";
    }

}
