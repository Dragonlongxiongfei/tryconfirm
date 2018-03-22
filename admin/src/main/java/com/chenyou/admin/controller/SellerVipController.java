package com.chenyou.admin.controller;

import com.chenyou.admin.domain.SellerVip;
import com.chenyou.admin.service.SellerVipService;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by amoslong on 2017/12/13.
 */
@RestController
@RequestMapping("sellervip")
public class SellerVipController {

    final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SellerVipService sellerVipService;

    @RequestMapping(method = RequestMethod.GET,value = "list")
    public ModelAndView list(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("seller/viplist");

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET,value = "data")
    public String data(HttpServletRequest request,@RequestParam(defaultValue = "0", required = false) int page,
                       @RequestParam(defaultValue = "50", required = false) int size){
        String result = null;
        try {
            result = sellerVipService.page(request, page, size);
        } catch(Exception  e) {
            JSONObject msg = new JSONObject();
            msg.put("error", e.getMessage());
            result = msg.toString();
            LOG.error("datalist error", e);
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "insert")
    @ResponseBody
    public String insert(SellerVip sellerVip){
        String jsonResult = "success";
        Map<String, String> result = new HashMap<>();
        result.put("result", jsonResult);
        try{
            sellerVipService.insert(sellerVip);
            jsonResult = JSONObject.fromObject(result).toString();
        }catch (Exception e ){
            LOG.error("class insert error: ", e);
            result.put("result", e.getMessage());
            jsonResult = JSONObject.fromObject(result).toString();
        }
        return jsonResult;
    }

    @RequestMapping(method = RequestMethod.POST,value = "update")
    @ResponseBody
    public String update(SellerVip sellerVip, HttpServletRequest request){
        String jsonResult = "success";
        Map<String, String> result = new HashMap<>();
        result.put("result", jsonResult);
        try{
            if (null != request.getParameter("vipId")) {
                sellerVip.setVipId(Integer.parseInt(request.getParameter("vipId")));
                sellerVipService.update(sellerVip);
            } else {
                sellerVipService.insert(sellerVip);
            }
//            sellerVip.setVipId(Integer.parseInt(request.getParameter("vipId")));
//            sellerVipService.update(sellerVip);
            jsonResult = JSONObject.fromObject(result).toString();
        }catch (Exception e ){
            LOG.error("class update error: ", e);
            result.put("result", e.getMessage());
            jsonResult = JSONObject.fromObject(result).toString();
        }
        return jsonResult;
    }
}
