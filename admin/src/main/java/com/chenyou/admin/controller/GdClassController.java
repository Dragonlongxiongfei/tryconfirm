package com.chenyou.admin.controller;

import com.chenyou.admin.Utils.ApplicationConstant;
import com.chenyou.admin.Utils.StringTools;
import com.chenyou.admin.domain.GdClass;
import com.chenyou.admin.service.GdClassService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by amoslong on 2017/12/11.
 */
@Controller
@RequestMapping("gdclass")
public class GdClassController {

    final static Logger LOG = Logger.getLogger(GdClassController.class);
    @Autowired
    private GdClassService gdClassService;

    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST},value = "list")
    public ModelAndView gdClassList(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("/seller/gdclass");
        String cId = request.getParameter("cId"),className = request.getParameter("className"),withVild = request.getParameter("with_Invalid");
        modelAndView.addObject("cId", StringUtils.defaultIfEmpty(cId,""));
        modelAndView.addObject("className", StringUtils.defaultIfEmpty(className,""));
        modelAndView.addObject("withVild", StringUtils.defaultIfEmpty(withVild,""));
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET,value = "data")
    @ResponseBody
    public String getPage(HttpServletRequest request,@RequestParam(defaultValue = "0", required = false) int page,
                          @RequestParam(defaultValue = "50", required = false) int size){
        String result = "";
        try{
            result = gdClassService.page(request,page,size);
        }catch (Exception e ){
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "insert")
    @ResponseBody
    public String insert(GdClass gdClass,@RequestParam("file") MultipartFile file,HttpServletRequest request){
        String jsonResult = "success";
        Map<String, String> result = new HashMap<>();
        result.put("result", jsonResult);
        if (!file.isEmpty()) {
            try {
                String storeDirectory = ApplicationConstant.CDN_URL + StringTools.dateToString(new Date(), "yyyyMMdd");
                String original1 = file.getOriginalFilename();
                int index = original1.lastIndexOf(".");
                String extend1 = original1.substring(index);
                String path = new Date().getTime() + RandomUtils.nextInt(10000) + extend1;
                File directory = new File(storeDirectory);
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                if (!file.isEmpty()) {
                    File lightImgFile = new File(storeDirectory + "/" + path);
                    file.transferTo(lightImgFile);
                }
                gdClass.setClassIcon(ApplicationConstant.IMG_URL + "/" + StringTools.dateToString(new Date(), "yyyyMMdd") + "/" + path.trim());
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                Date date=new Date();
                String str=sdf.format(date);
                gdClass.setAddTime(str);
                gdClassService.insert(gdClass);
                jsonResult = JSONObject.fromObject(result).toString();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                result.put("result", e.getMessage());
                jsonResult = JSONObject.fromObject(result).toString();
            } catch (IOException e) {
                e.printStackTrace();
                result.put("result", e.getMessage());
                jsonResult = JSONObject.fromObject(result).toString();
            }
            return jsonResult;
        } else {
            return "上传失败，因为文件是空的.";
        }
    }

    @RequestMapping(method = RequestMethod.POST,value = "update")
    @ResponseBody
    public String update(GdClass gdClass,HttpServletRequest request,@RequestParam("file") MultipartFile file){
        String jsonResult = "success";
        Map<String, String> result = new HashMap<>();
        result.put("result", jsonResult);
        try{
            String original1 = file.getOriginalFilename();
            int index = original1.lastIndexOf(".");
            String extend1 = original1.substring(index);
            String path = new Date().getTime() + RandomUtils.nextInt(10000) + extend1;
            String storeDirectory = ApplicationConstant.CDN_URL + StringTools.dateToString(new Date(), "yyyyMMdd");
            File directory = new File(storeDirectory);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            if (!file.isEmpty()) {
                File lightImgFile = new File(storeDirectory + "/" + path);
                file.transferTo(lightImgFile);
            }
            gdClass.setClassIcon(ApplicationConstant.IMG_URL + "/" + StringTools.dateToString(new Date(), "yyyyMMdd") + "/" + path.trim());
            String cid = request.getParameter("classId");
            gdClass.setcId(Integer.parseInt(cid));
            gdClassService.update(gdClass);
            jsonResult = JSONObject.fromObject(result).toString();
        }catch (Exception e ){
            LOG.error("class update error: ", e);
            result.put("result", e.getMessage());
            jsonResult = JSONObject.fromObject(result).toString();
        }
        return jsonResult;
    }
}
